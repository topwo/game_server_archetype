package com.kidbear.logical.net.rpc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The type Motan server.
 */
public class MotanServer {

	/**
	 * Init motan rpc.
	 */
	@SuppressWarnings("resource")
	public static void initMotanRpc() {
		new ClassPathXmlApplicationContext("classpath:spring-motan/motan_server.xml");
		System.out.println("motan server start...");
	}

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("classpath:spring-motan/motan_server.xml");
		System.out.println("motan server start...");
	}
}