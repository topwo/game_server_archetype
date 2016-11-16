#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.kidbear.${artifactId}.core;

import com.kidbear.${artifactId}.task.ExecutorPool;
import com.kidbear.${artifactId}.util.Config;
import com.kidbear.${artifactId}.util.csv.CsvDataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 服务器启动类
 */
public class GameInit {
    /**
     * The constant serverId.
     */
    public static int serverId = 1; // 服务器标示
    /**
     * The constant confFileBasePath.
     */
    public static String confFileBasePath = "/csv/";// 配置文件根目录
    /**
     * The constant cfg.
     */
    public static Config cfg;// 读取server.properties配置
    /**
     * The constant templatePacket.
     */
    public static String templatePacket = "com.kidbear.${artifactId}.template.";// xml模板类的位置
    /**
     * The constant dataConfig.
     */
    public static String dataConfig = "/dataConfig.xml";// xml位置
    private static final Logger logger = LoggerFactory
            .getLogger(GameInit.class);
    /**
     * The constant serverStartTime.
     */
    public static String serverStartTime = "server_start_time";// 记录开服时间

    /**
     * Init boolean.
     *
     * @return the boolean
     */
    public static boolean init() {
        try {
            logger.info("================开启逻辑服务器================");
            // TODO
            // 加载服务器配置文件
            logger.info("加载服务器配置文件");
            confInit();
            // 加载Redis
            logger.info("加载Redis");
//            Redis.getInstance().init();
//            try {
//                Redis.getInstance().test();
//            } catch (Exception e) {
//                logger.info("Redis未启动，初始化异常", e);
//                return false;
//            }
            // 记录服务器开服时间
//            if (!Redis.getInstance().hexist(Redis.GLOBAL_DB, serverStartTime,
//                    GameInit.serverId + "")) {
//                Redis.getInstance()
//                        .hset(Redis.GLOBAL_DB, serverStartTime,
//                                GameInit.serverId + "",
//                                System.currentTimeMillis() + "");
//            }
            // memcached
            logger.info("加载Memcached");
//            MemcachedCRUD.getInstance().init();
            // 加载hibernate
            logger.info("加载hibernate");
//            HibernateUtil.init();
            // 加载CSV数据
            logger.info("加载数据配置");
            CsvDataLoader.getInstance(templatePacket, dataConfig).load();
            // 加载敏感词语
            logger.info("加载敏感词语");
//            SensitiveFilter.getInstance();
            // 初始化消息路由和系统模块
            logger.info("初始化模块");
            Router.getInstance().initMgr();
            logger.info("初始化CSV表");
            Router.getInstance().initCsvData();
            logger.info("初始化数值");
            Router.getInstance().initData();
            // 初始化线程池
            logger.info("初始化线程池");
            ExecutorPool.initThreadsExecutor();
            // 启动服务器
            logger.info("启动服务器");
            GameServer.getInstance().startServer();
            // 初始化后台管理账号
            logger.info("初始化后台管理账号");
//            if (HibernateUtil.find(Admin.class, 1) == null) {
//                Admin admin = new Admin();
//                admin.setId(1l);
//                admin.setName("kidbear");
//                admin.setPwd("123321");
//                HibernateUtil.insert(admin, admin.getId());
//            }
            logger.info("初始化后台访客账号");
//            if (HibernateUtil.find(Admin.class, 1) == null) {
//                Admin admin = new Admin();
//                admin.setId(2l);
//                admin.setName("guest");
//                admin.setPwd("123456");
//                HibernateUtil.insert(admin, admin.getId());
//            }
            logger.info("================完成开启逻辑服务器================");
            // 记录服务器启动时间
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
//            MemcachedCRUD.getInstance().saveObject(
//                    "ServerStartTime" + GameInit.serverId,
//                    formatter.format(currentTime));
            return true;
        } catch (Throwable e) {
            CoreServlet.logger.error("初始化异常:{}", e);
            return false;
        }
    }

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args) {
        GameInit.init();
    }

    /**
     * Conf init.
     */
    public static void confInit() {
        cfg = new Config();
        cfg.loadConfig();
        serverId = cfg.get("serverId", serverId);
    }

    /**
     * Shutdown boolean.
     *
     * @return the boolean
     */
    public static boolean shutdown() {
        logger.info("================关闭逻辑服务器================");
        logger.info("关闭redis连接");
//        Redis.destroy();
        logger.info("关闭memcached连接");
//        MemcachedCRUD.destroy();
        logger.info("关闭线程池");
        ExecutorPool.shutdown();
        // 关闭逻辑服务器
        logger.info("关闭逻辑服务器");
        GameServer.getInstance().shutServer();
        logger.info("================完成关闭服务器================");
        return true;
    }
}
