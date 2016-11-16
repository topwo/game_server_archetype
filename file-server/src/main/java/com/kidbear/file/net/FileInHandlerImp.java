package com.kidbear.file.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kidbear.file.file.FileService;
import com.kidbear.file.file.FileServiceImply;
import com.kidbear.file.task.ExecutorPool;

public class FileInHandlerImp {
	public Logger logger = LoggerFactory.getLogger(FileInHandlerImp.class);
	public static volatile boolean CODE_DEBUG = false;

	public void channelRead(final ChannelHandlerContext ctx, final Object msg) {
		ExecutorPool.channelHandleThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				DefaultFullHttpRequest req = (DefaultFullHttpRequest) msg;
				if (req.getMethod() == HttpMethod.POST) {
					try {
						postHandle(ctx, req);
					} catch (IOException e) {
						logger.error("file post error", e);
					}
				} else {
					try {
						getHandle(ctx, req);
					} catch (Exception e) {
						logger.error("file get error", e);
					}
				}
			}
		});
	}

	public void getHandle(ChannelHandlerContext ctx, DefaultFullHttpRequest req)
			throws IOException {
		QueryStringDecoder decoder = new QueryStringDecoder(req.getUri());
		Map<String, List<String>> params = decoder.parameters();
		logger.info("ip:{},read :{}", ctx.channel().remoteAddress(), params);
		List<String> baseList = params.get("base");
		List<String> channelList = params.get("channel");
		FileService service = FileServiceImply.getInstance();
		service.download(channelList.get(0), baseList.get(0), ctx);
	}

	public void postHandle(ChannelHandlerContext ctx, DefaultFullHttpRequest req)
			throws IOException {
		HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(
				new DefaultHttpDataFactory(false), req);
		try {
			logger.info("ip:{},read :{}", ctx.channel().remoteAddress(),
					decoder.getBodyHttpDatas());
			InterfaceHttpData base = decoder.getBodyHttpData("base");
			InterfaceHttpData channel = decoder.getBodyHttpData("channel");
			if (base != null && channel != null) {
				FileService service = FileServiceImply.getInstance();
				service.download(((Attribute) channel).getValue(),
						((Attribute) base).getValue(), ctx);
			}
		} catch (Exception e) {
			logger.error("download error msg:", e);
			e.printStackTrace();
		}
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		logger.error("exceptionCaught:{}", cause);
	}

	public void messageReceived(ChannelHandlerContext ctx, FullHttpRequest msg)
			throws Exception {
		logger.info("messageReceived");
	}
}
