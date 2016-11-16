package com.kidbear.basic.net.http;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kidbear.basic.util.Constants;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultFullHttpResponse;

public class HttpOutHandlerImp {
	public Logger logger = LoggerFactory.getLogger(HttpOutHandlerImp.class);

	public void write(ChannelHandlerContext ctx, Object msg,
			ChannelPromise promise) throws Exception {
		if (Constants.MSG_LOG_DEBUG) {
			DefaultFullHttpResponse resp = (DefaultFullHttpResponse) msg;
			logger.info("ip:{},write:{}", ctx.channel().remoteAddress(), resp
					.content().toString(Charset.forName("UTF-8")));
		}
	}
}
