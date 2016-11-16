package it.pkg.net;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

public class RpcHandlerImp {
	public Logger logger = LoggerFactory.getLogger(RpcHandlerImp.class);
	public static volatile boolean CODE_DEBUG = false;

	public void channelRead(final ChannelHandlerContext ctx, final Object msg) {
		DefaultFullHttpRequest req = (DefaultFullHttpRequest) msg;
		String rpcType = req.headers().get("Rpc-Type");
		if (rpcType != null && rpcType.equals("basic")
				&& req.getMethod() == HttpMethod.POST) {
			try {
				handle(ctx, req.content());
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}

	public void handle(ChannelHandlerContext ctx, ByteBuf content)
			throws IOException {
		RpcServer.rpcServer.handle(new ByteBufInputStream(content),
				new ByteBufOutputStream(content));
		writeJSON(ctx, HttpResponseStatus.OK, content);
		ctx.flush();
	}

	private static void writeJSON(ChannelHandlerContext ctx,
			HttpResponseStatus status, ByteBuf content/* , boolean isKeepAlive */) {
		if (ctx.channel().isWritable()) {
			FullHttpResponse msg = null;
			if (content != null) {
				msg = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status,
						content);
				msg.headers().set(HttpHeaders.Names.CONTENT_TYPE,
						"application/json; charset=utf-8");
			} else {
				msg = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status);
			}
			if (msg.content() != null) {
				msg.headers().set(HttpHeaders.Names.CONTENT_LENGTH,
						msg.content().readableBytes());
			}
			// not keep-alive
			ctx.write(msg).addListener(ChannelFutureListener.CLOSE);
		}
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
	}

	public void messageReceived(ChannelHandlerContext ctx, FullHttpRequest msg)
			throws Exception {

	}
}
