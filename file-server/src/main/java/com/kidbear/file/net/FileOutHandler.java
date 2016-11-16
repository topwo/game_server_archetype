package com.kidbear.file.net;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class FileOutHandler extends ChannelHandlerAdapter {

	public FileOutHandlerImp handler = new FileOutHandlerImp();

	@Override
	public void write(ChannelHandlerContext ctx, Object msg,
			ChannelPromise promise) throws Exception {
		super.write(ctx, msg, promise);
		handler.write(ctx, msg, promise);
	}

}
