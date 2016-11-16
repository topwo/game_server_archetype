package it.pkg.client.rpc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MotanClient {
	private static ApplicationContext ctx;

	@SuppressWarnings("resource")
	public static <T> T getService(Class<T> clz) {
		ctx = ctx == null ? new ClassPathXmlApplicationContext(
				"classpath:spring-motan/motan_client.xml") : ctx;
		return ctx.getBean(clz);
	}

}