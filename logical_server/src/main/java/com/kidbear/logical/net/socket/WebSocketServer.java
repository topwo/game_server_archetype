package com.kidbear.logical.net.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.concurrent.Executors;

/**
 * @author lilinfeng
 * @date 2014年2月14日
 * @version 1.0
 */
public class WebSocketServer {
	public Logger log = LoggerFactory.getLogger(WebSocketServer.class);
	public static WebSocketServer inst;
	public static Properties p;
	public static int port;
	private NioEventLoopGroup bossGroup;
	private NioEventLoopGroup workGroup;

	private WebSocketServer() {
	}

	public static WebSocketServer getInstance() {
		if (inst == null) {
			inst = new WebSocketServer();
			inst.initData();
		}
		return inst;
	}

	public void initData() {
		try {
			p = readProperties();
			port = Integer.parseInt(p.getProperty("port"));
		} catch (IOException e) {
			log.error("socket配置文件读取错误");
			e.printStackTrace();
		}
	}
public static void main(String[] args) {
	WebSocketServer.getInstance().start();
}
	public void start() {
		bossGroup = new NioEventLoopGroup(0, Executors.newCachedThreadPool());// boss线程组
		workGroup = new NioEventLoopGroup(0, Executors.newCachedThreadPool());// work线程组
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup, workGroup);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast("http-codec", new HttpServerCodec());
				pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
				ch.pipeline()
						.addLast("http-chunked", new ChunkedWriteHandler());
				pipeline.addLast("handler", new WebSocketServerHandler());
			}
		});
		// 启动端口
		ChannelFuture future;
		try {
			future = bootstrap.bind(port).sync();
			if (future.isSuccess()) {
				log.info("端口{}已绑定", port);
			}
		} catch (InterruptedException e) {
			log.info("端口{}绑定失败", port);
		}
	}

	public void shut() {
		workGroup.shutdownGracefully();
		workGroup.shutdownGracefully();
		// 关闭所有channel连接
		log.info("关闭所有channel连接");
		ChannelMgr.getInstance().closeAllChannel();
		log.info("端口{}已解绑", port);
	}

	/**
	 * 读配置socket文件
	 * 
	 * @return
	 * @throws IOException
	 */
	protected Properties readProperties() throws IOException {
		Properties p = new Properties();
		InputStream in = SocketServer.class
				.getResourceAsStream("/net.properties");
		Reader r = new InputStreamReader(in, Charset.forName("UTF-8"));
		p.load(r);
		in.close();
		return p;
	}
}
