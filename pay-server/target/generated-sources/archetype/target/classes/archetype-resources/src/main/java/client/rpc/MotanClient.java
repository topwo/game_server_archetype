#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.rpc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MotanClient {
	private static ApplicationContext ctx;
	static {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:spring-motan/motan_client.xml");
	}

	public static <T> T getService(Class<T> clz) {
		return ctx.getBean(clz);
	}

}