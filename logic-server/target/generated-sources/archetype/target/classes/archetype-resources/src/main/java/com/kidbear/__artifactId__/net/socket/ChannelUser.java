#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.kidbear.${artifactId}.net.socket;

import io.netty.channel.ChannelHandlerContext;

public class ChannelUser {
	public Long channelId;
	public Long userId;
	public ChannelHandlerContext ctx;
}
