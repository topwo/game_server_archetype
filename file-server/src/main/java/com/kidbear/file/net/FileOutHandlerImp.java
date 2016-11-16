package com.kidbear.file.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class FileOutHandlerImp {
	public Logger logger = LoggerFactory.getLogger(FileOutHandlerImp.class);

	public void write(ChannelHandlerContext ctx, Object msg,
			ChannelPromise promise) throws Exception {
		// DefaultFullHttpResponse resp = (DefaultFullHttpResponse) msg;
		// logger.info("write:{}",
		// resp.content().toString(Charset.forName("UTF-8")));
	}
}
