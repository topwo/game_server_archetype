package com.kidbear.pay.core;

import com.kidbear.pay.task.ExecutorPool;
import com.kidbear.pay.util.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 何金成
 * @ClassName: GameInit
 * @Description: 服务器初始化
 * @date 2015年5月23日 下午4:21:19
 */
public class GameInit {
    public static String confFileBasePath = "/";// 配置文件根目录
    public static Config cfg;// 读取server.properties配置
    public static final Logger logger = LoggerFactory.getLogger(GameInit.class);

    public static void init() {
        try {
            logger.info("================开启支付服务器================");
            // 加载服务器配置文件
            logger.info("加载服务器配置文件");
            confInit();
            // 加载Redis
//			Redis.getInstance().init();
//			try {
//				Redis.getInstance().test();
//			} catch (Exception e) {
//				logger.info("Redis未启动，初始化异常");
//			}
            // ExecutorPool
            logger.info("加载线程池");
            ExecutorPool.initThreadsExecutor();
            // memcached
//			MemcachedCRUD.getInstance().init();
            // 加载hibernate
            logger.info("加载hibernate");
//			HibernateUtil.init();
            // 记录服务器开启时间
//			Date currentTime = new Date();
//			SimpleDateFormat formatter = new SimpleDateFormat(
//					"yyyy-MM-dd HH:mm:ss");
//			MemcachedCRUD.getInstance().saveObject("ServerStartTimePay",
//					formatter.format(currentTime));
            logger.info("================完成开启支付服务器================");
        } catch (Throwable e) {
            CoreServlet.logger.error("初始化异常 {}", e);
            System.exit(0);
        }
    }

    public void shut() {
//        MemcachedCRUD.destroy();
//        Redis.destroy();
        ExecutorPool.shutdown();
    }

    public static void confInit() {
        cfg = new Config();
        cfg.loadConfig();
    }
}
