package com.kidbear.logical.net.rpc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The type Motan client.
 */
public class MotanClient {
	private static ApplicationContext ctx;
	static {
		ctx = new ClassPathXmlApplicationContext(
				"classpath:spring-motan/motan_client.xml");
	}

	/**
	 * Gets service.
	 *
	 * @param <T> the type parameter
	 * @param clz the clz
	 * @return the service
	 */
	public static <T> T getService(Class<T> clz) {
		return ctx.getBean(clz);
	}

}