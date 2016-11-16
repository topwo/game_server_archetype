#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core;

import ${package}.util.Config;
import ${package}.util.sensitive.SensitiveFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Properties;

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
    public static String fileIp;
    public static int filePort;
    public static final String HOT_FIX = "hotfix";
    public static final String HOT_FIX_BASE = "hotfix_base_channel_";
    public static Properties versionProperties;

    public static void init() {
        try {
            logger.info("================开启管理服务器================");
            // 加载服务器配置文件
            logger.info("加载服务器配置文件");
            confInit();
            // 加载敏感词语
            logger.info("加载敏感词语");
            SensitiveFilter.getInstance();
            // 加载Redis
//			try {
//				Redis.getInstance().test();
//			} catch (Exception e) {
//				logger.info("Redis未启动，初始化异常", e);
//			}
            // memcached
//			MemcachedCRUD.getInstance();
            // 加载hibernate
//			logger.info("加载hibernate");
//			HibernateUtil.init();
            // 检查版本号
            logger.info("检查版本号");
            // String baseVersion = cfg.get("baseVersion");
            // for (Integer channel : ChannelCode.channels) {
            // if (!Redis.getInstance().hexist(Redis.GLOBAL_DB, HOT_FIX,
            // channel + "${symbol_pound}" + baseVersion)) {
            // Redis.getInstance().hset(Redis.GLOBAL_DB, HOT_FIX,
            // channel + "${symbol_pound}" + baseVersion, "0.0.0${symbol_pound}0");
            // Redis.getInstance().zadd(Redis.GLOBAL_DB,
            // HOT_FIX_BASE + channel, System.currentTimeMillis(),
            // channel + "${symbol_pound}" + baseVersion);
            // }
            // }
//			versionProperties = readVersionProperties();
//			for (Integer channel : ChannelCode.channels) {
//				String baseVersion = versionProperties
//						.getProperty(channel + "");
//				if (!Redis.getInstance().hexist(Redis.GLOBAL_DB, HOT_FIX,
//						channel + "${symbol_pound}" + baseVersion)) {
//					Redis.getInstance().hset(Redis.GLOBAL_DB, HOT_FIX,
//							channel + "${symbol_pound}" + baseVersion, "${version}.0${symbol_pound}0");
//					Redis.getInstance().zadd(Redis.GLOBAL_DB,
//							HOT_FIX_BASE + channel, System.currentTimeMillis(),
//							channel + "${symbol_pound}" + baseVersion);
//				}
//			}
            logger.info("================完成开启管理服务器================");
//			Date currentTime = new Date();
//			SimpleDateFormat formatter = new SimpleDateFormat(
//					"yyyy-MM-dd HH:mm:ss");
//			MemcachedCRUD.getInstance().saveObject("ServerStartTimeRouter",
//					formatter.format(currentTime));
        } catch (Throwable e) {
            CoreServlet.logger.error("初始化异常 {}", e);
            System.exit(0);
        }
    }

    public static void confInit() {
        cfg = new Config();
        cfg.loadConfig();
        fileIp = cfg.get("fileIp");
        filePort = cfg.get("filePort", 8300);
    }

    protected static Properties readVersionProperties() throws IOException {
        Properties p = new Properties();
        InputStream in = GameInit.class
                .getResourceAsStream("/version.properties");
        Reader r = new InputStreamReader(in, Charset.forName("UTF-8"));
        p.load(r);
        in.close();
        return p;
    }
}
