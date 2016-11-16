#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.kidbear.${artifactId}.net.socket;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kidbear.${artifactId}.core.GameServer;
import com.kidbear.${artifactId}.manager.account.AccountMgr;
import com.kidbear.${artifactId}.util.Constants;
import com.kidbear.${artifactId}.util.JsonUtils;
import com.kidbear.${artifactId}.util.encrypt.XXTeaCoder;

public class SocketHandlerImp {
	private static Logger log = LoggerFactory.getLogger(SocketHandlerImp.class);
	public static volatile boolean ENCRIPT_DECRIPT = true;

	public void channelRead(final ChannelHandlerContext ctx, final Object msg)
			throws Exception {
		SocketServer.handleTaskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				if (!GameServer.shutdown) {// 服务器开启的情况下
					dataHandle(ctx, msg);
				} else {// 服务器已关闭
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("errMsg", "server closed");
					writeJSON(ctx, jsonObject);
				}
			}

		});
	}

	/**
	 * @Title: codeFilter
	 * @Description: 编解码过滤
	 * @param val
	 * @return String
	 * @throws
	 */
	private String codeFilter(String val) {
		try {
			val = val.contains("%") ? URLDecoder.decode(val, "UTF-8") : val;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String valTmp = val;
		val = ENCRIPT_DECRIPT ? XXTeaCoder.decryptBase64StringToString(val,
				XXTeaCoder.key) : val;
		if (Constants.MSG_LOG_DEBUG) {
			if (val == null) {
				val = valTmp;
			}
			log.info("server received : {}", val);
		}
		return val;
	}

	/**
	 * @Title: dataHandle
	 * @Description: 数据处理
	 * @param ctx
	 * @param msg
	 *            void
	 * @throws
	 */
	private void dataHandle(final ChannelHandlerContext ctx, final Object msg) {
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, CharsetUtil.UTF_8);
		body = codeFilter(body);
		if (Constants.MSG_LOG_DEBUG) {
			log.info("server received: " + body);
		}
		// Router.getInstance().route(body, ctx);
	}

	public static void writeJSON(ChannelHandlerContext ctx, Object msg) {
		String sentMsg = JsonUtils.objectToJson(msg);
		if (Constants.MSG_LOG_DEBUG) {
			log.info("server sent : {}", sentMsg);
		}
		if (ctx.channel().isWritable()) {
			ByteBuf resp = Unpooled.copiedBuffer(sentMsg
					.getBytes(CharsetUtil.UTF_8));
			ctx.write(resp);
			ctx.flush();
		}
	}

	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise)
			throws Exception {
		AccountMgr.getInstance().logout(ctx);
	}

	public void messageReceived(ChannelHandlerContext ctx, String msg)
			throws Exception {
	}
}
