package it.pkg.net;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.pkg.client.basic.IPay;

public class MotanServer {

	@SuppressWarnings("resource")
	public static void initMotanRpc() {
		new ClassPathXmlApplicationContext("classpath:spring-motan/motan_server.xml");
		System.out.println("motan server start...");
	}

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("classpath:spring-motan/motan_server.xml");
		System.out.println("motan server start...");
	}
}