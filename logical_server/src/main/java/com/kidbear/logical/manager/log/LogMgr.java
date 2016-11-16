package com.kidbear.logical.manager.log;

import com.alibaba.fastjson.JSON;
import com.kidbear.logical.task.ExecutorPool;
import com.kidbear.logical.util.redis.Redis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * The type Log mgr.
 *
 * @author 何金成
 * @ClassName: LogMgr
 * @Description: 日志记录 ，记录游戏中的日志信息
 */
public class LogMgr {
    /**
     * The constant logMgr.
     */
    protected static LogMgr logMgr;
    /**
     * The Logger.
     */
    public Logger logger = LoggerFactory.getLogger(LogMgr.class);
    /**
     * The constant PAY_COST_LOG.
     */
    public static final String PAY_COST_LOG = "pay_cost_log";
    /**
     * The constant CREATE_LOGIN_ROLE_LOG.
     */
    public static final String CREATE_LOGIN_ROLE_LOG = "create_login_role_log";
    /**
     * The constant REMAIN_LOG.
     */
    public static final String REMAIN_LOG = "remain_log";
    /**
     * The constant DAILY_PAY_LOG.
     */
    public static final String DAILY_PAY_LOG = "daily_pay_log";
    /**
     * The constant ACTIVE_LOG.
     */
    public static final String ACTIVE_LOG = "active_log";
    /**
     * The constant IP_LOG.
     */
    public static final String IP_LOG = "ip_log";
    /**
     * The constant IP_SET.
     */
    public static final String IP_SET = "ip_set";
    /**
     * The Redis.
     */
    public Redis redis = Redis.getInstance();

    /**
     * Instantiates a new Log mgr.
     */
    protected LogMgr() {
        IPLog.load("ipdb.dat");
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static LogMgr getInstance() {
        if (logMgr == null) {
            logMgr = new LogMgr();
        }
        return logMgr;
    }

    /**
     * ip对应地域日志记录
     *
     * @param ip void
     * @throws
     * @Title: ipLog
     * @Description: ip记录
     */
    public void ipLog(final String ip) {
        ExecutorPool.ipLogThread.execute(new Runnable() {
            @Override
            public void run() {
                String ipLogInfo = ip.replace("/", "");
                ipLogInfo = ipLogInfo.split(":")[0];
                if (!Redis.getInstance().sexist(Redis.GLOBAL_DB, IP_SET,
                        ipLogInfo)) {
                    String[] ipInfos = IPLog.find(ipLogInfo);
                    StringBuffer ipInfo = new StringBuffer();
                    for (String ip : ipInfos) {
                        ipInfo.append(ip).append("#");
                    }
                    String key = ipInfo.substring(0,
                            ipInfo.toString().length() - 1);
                    Redis.getInstance()
                            .hincrBy(Redis.GLOBAL_DB, IP_LOG, key, 1);
                    Redis.getInstance()
                            .sadd(Redis.GLOBAL_DB, IP_SET, ipLogInfo);
                }
            }
        });
    }

    /**
     * 消费类型日志记录
     *
     * @param costType the cost type
     * @throws
     * @Title: yuanbaoCostLog
     * @Description: RMB消费统计
     */
    public void payCostLog(final int costType) {
        ExecutorPool.payLogThread.execute(new Runnable() {
            @Override
            public void run() {
                int costCount = redis.hexist(Redis.LOGIC_DB, PAY_COST_LOG,
                        costType + "") ? JSON.parseObject(
                        redis.hget(Redis.LOGIC_DB, PAY_COST_LOG, costType + ""),
                        Integer.class)
                        : 0;
                costCount += 1;
                redis.hset(Redis.LOGIC_DB, PAY_COST_LOG, costType + "",
                        costCount + "");
            }
        });
    }

    /**
     * Create role log.
     *
     * @param userid the userid
     */
    public void createRoleLog(final long userid) {
        ExecutorPool.eventLogThread.execute(new Runnable() {
            @Override
            public void run() {
                int year = new Date().getYear() + 1900;
                int month = new Date().getMonth() + 1;
                int day = new Date().getDate();
                String createDate = year + "-" + month + "-" + day;
                redis.hset(Redis.LOGIC_DB, CREATE_LOGIN_ROLE_LOG, userid + "",
                        createDate + "#" + createDate);
                RemainInfo remainInfo = redis.hexist(Redis.LOGIC_DB,
                        REMAIN_LOG, createDate) ? JSON.parseObject(
                        redis.hget(Redis.LOGIC_DB, REMAIN_LOG, createDate),
                        RemainInfo.class) : new RemainInfo();
                remainInfo.setCreateCount(remainInfo.getCreateCount() + 1);
                redis.hset(Redis.LOGIC_DB, REMAIN_LOG, createDate,
                        JSON.toJSONString(remainInfo));
                // 记录今日活跃
                int activeNum = redis.hexist(Redis.LOGIC_DB, ACTIVE_LOG,
                        createDate) ? Integer.parseInt(redis.hget(
                        Redis.LOGIC_DB, ACTIVE_LOG, createDate)) : 0;
                activeNum += 1;
                redis.hset(Redis.LOGIC_DB, ACTIVE_LOG, createDate, activeNum
                        + "");
            }
        });
    }

    /**
     * Remain log.
     *
     * @param userid the userid
     */
    public void remainLog(final long userid) {
        ExecutorPool.eventLogThread.execute(new Runnable() {
            @Override
            public void run() {
                Date date = new Date();
                int year = date.getYear() + 1900;
                int month = date.getMonth() + 1;
                int day = date.getDate();
                String nowDate = year + "-" + month + "-" + day;
                String createLogin = redis.hexist(Redis.LOGIC_DB,
                        CREATE_LOGIN_ROLE_LOG, userid + "") ? redis.hget(
                        Redis.LOGIC_DB, CREATE_LOGIN_ROLE_LOG, userid + "")
                        : nowDate + "#" + nowDate;
                String createDate = createLogin.split("#")[0];
                String loginDate = createLogin.split("#")[1];
                if (loginDate.equals(nowDate)) {
                    return;
                }
                // 记录今日登录
                redis.hset(Redis.LOGIC_DB, CREATE_LOGIN_ROLE_LOG, userid + "",
                        createDate + "#" + nowDate);
                // 记录今日活跃
                int activeNum = redis.hexist(Redis.LOGIC_DB, ACTIVE_LOG,
                        nowDate) ? Integer.parseInt(redis.hget(Redis.LOGIC_DB,
                        ACTIVE_LOG, nowDate)) : 0;
                activeNum += 1;
                redis.hset(Redis.LOGIC_DB, ACTIVE_LOG, nowDate, activeNum + "");
                RemainInfo remainInfo = redis.hexist(Redis.LOGIC_DB,
                        REMAIN_LOG, createDate) ? JSON.parseObject(
                        redis.hget(Redis.LOGIC_DB, REMAIN_LOG, createDate),
                        RemainInfo.class) : new RemainInfo();
                // 次日留存
                Calendar secondCalendar = Calendar.getInstance();
                secondCalendar.setTime(date);
                secondCalendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
                String secondDate = (secondCalendar.getTime().getYear() + 1900)
                        + "-" + (secondCalendar.getTime().getMonth() + 1) + "-"
                        + secondCalendar.getTime().getDate();
                if (createDate.equals(secondDate)) {
                    remainInfo.setSecondCount(remainInfo.getSecondCount() + 1);
                }
                // 七日留存
                Calendar seventhCalendar = Calendar.getInstance();
                seventhCalendar.setTime(date);
                seventhCalendar.add(Calendar.DAY_OF_MONTH, -6); // 设置为前一天
                String seventhDate = (seventhCalendar.getTime().getYear() + 1900)
                        + "-"
                        + (seventhCalendar.getTime().getMonth() + 1)
                        + "-" + seventhCalendar.getTime().getDate();
                if (createDate.equals(seventhDate)) {
                    remainInfo.setSeventhCount(remainInfo.getSeventhCount() + 1);
                }
                redis.hset(Redis.LOGIC_DB, REMAIN_LOG, createDate,
                        JSON.toJSONString(remainInfo));
            }
        });
    }

    /**
     * Daily pay log.
     *
     * @param rmb void
     * @throws
     * @Title: dailyPayLog
     * @Description: 每日充值总金额
     */
    public void dailyPayLog(final int rmb) {
        ExecutorPool.payLogThread.execute(new Runnable() {
            @Override
            public void run() {
                Date date = new Date();
                int year = date.getYear() + 1900;
                int month = date.getMonth() + 1;
                int day = date.getDate();
                String nowDate = year + "-" + month + "-" + day;
                int rmbSum = redis.hexist(Redis.LOGIC_DB, DAILY_PAY_LOG,
                        nowDate) ? Integer.parseInt(redis.hget(Redis.LOGIC_DB,
                        DAILY_PAY_LOG, nowDate)) : 0;
                rmbSum += rmb;
                redis.hset(Redis.LOGIC_DB, DAILY_PAY_LOG, nowDate, rmbSum + "");
            }
        });
    }
}
