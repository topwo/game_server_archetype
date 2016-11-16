package com.kidbear.basic.manager.account;

import com.alibaba.fastjson.JSONObject;
import com.kidbear.basic.net.ProtoMessage;
import com.kidbear.basic.net.http.HttpInHandler;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 账号管理
 */
public class AccountMgr {
    /**
     * The constant accountMgr.
     */
    protected static AccountMgr accountMgr;
    /**
     * The constant logger.
     */
    protected static final Logger logger = LoggerFactory
            .getLogger(AccountMgr.class);

    /**
     * Instantiates a new Account mgr.
     */
    protected AccountMgr() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static AccountMgr getInstance() {
        if (null == accountMgr) {
            accountMgr = new AccountMgr();
        }
        return accountMgr;
    }

    /**
     * Init data.
     */
    public void initData() {
        logger.info("AccountMgr initData");
    }

    /**
     * Login.
     *
     * @param ctx    the ctx
     * @param userid the userid
     */
    public void login(ChannelHandlerContext ctx, long userid) {
        // TODO
    }

    /**
     * Logout.
     *
     * @param ctx the ctx
     * @return void
     */
    public void logout(ChannelHandlerContext ctx) {
        // ChannelMgr.getInstance().removeChannel(ctx);
    }

    /**
     * Test.
     *
     * @param ctx the ctx
     */
    public void test(ChannelHandlerContext ctx, JSONObject data, long userid) {
        logger.info("{} test method from :{},msg:{}", ctx.channel().remoteAddress(), userid, data.toJSONString());
        HttpInHandler.writeJSON(ctx, ProtoMessage.getSuccessResp());
    }
}
