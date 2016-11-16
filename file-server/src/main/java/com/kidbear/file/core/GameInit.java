package com.kidbear.file.core;

import com.kidbear.file.net.FileServer;
import com.kidbear.file.task.ExecutorPool;
import com.kidbear.file.util.Config;
import com.kidbear.file.util.memcached.MemcachedCRUD;
import com.kidbear.file.util.redis.Redis;
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
    private static final Logger logger = LoggerFactory
            .getLogger(GameInit.class);

    public static void init() {
        try {
            logger.info("================开启文件服务器================");
            // 加载服务器配置文件
            logger.info("加载服务器配置文件");
            confInit();
            // 加载敏感词语
            // 加载Redis
//			Redis.getInstance().init();
//			try {
//				Redis.getInstance().test();
//			} catch (Exception e) {
//				logger.info("Redis未启动，初始化异常");
//			}

            // memcached
//			MemcachedCRUD.getInstance().init();
            // 加载Rpc服务
            FileServer.getInstance().start();
            // 初始化线程池
            ExecutorPool.initThreadsExecutor();
            logger.info("================完成开启文件服务器================");
            // 记录服务器开启时间
//			Date currentTime = new Date();
//			SimpleDateFormat formatter = new SimpleDateFormat(
//					"yyyy-MM-dd HH:mm:ss");
//			MemcachedCRUD.getInstance().saveObject("ServerStartTimeFile",
//					formatter.format(currentTime));
        } catch (Throwable e) {
            CoreServlet.logger.error("初始化异常 {}", e);
            System.exit(0);
        }
    }

    public void shut() {
        FileServer.getInstance().shut();
        Redis.destroy();
        MemcachedCRUD.destroy();
        ExecutorPool.shutdown();
        FileServer.getInstance().shut();
    }

    public static void confInit() {
        cfg = new Config();
        cfg.loadConfig();
    }
}
