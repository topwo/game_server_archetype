package com.kidbear.logical.net;

import io.netty.channel.ChannelHandlerContext;

public class Message {
	public ProtoMessage msg;
	public ChannelHandlerContext ctx;

	public Message() {
	}

	public Message(ProtoMessage msg, ChannelHandlerContext ctx) {
		this.msg = msg;
		this.ctx = ctx;
	}
}
