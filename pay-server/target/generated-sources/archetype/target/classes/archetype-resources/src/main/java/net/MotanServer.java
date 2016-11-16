#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.net;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ${package}.client.${artifactId}.IPay;

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