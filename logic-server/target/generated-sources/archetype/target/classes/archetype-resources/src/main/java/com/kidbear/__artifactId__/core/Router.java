#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.kidbear.${artifactId}.core;

import com.alibaba.fastjson.JSONObject;
import com.kidbear.${artifactId}.manager.account.AccountMgr;
import com.kidbear.${artifactId}.manager.log.LogMgr;
import com.kidbear.${artifactId}.net.ProtoIds;
import com.kidbear.${artifactId}.net.ProtoMessage;
import com.kidbear.${artifactId}.net.http.HttpInHandler;
import com.kidbear.${artifactId}.util.Constants;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 何金成
 * @ClassName: Router
 * @Description: 消息路由分发
 * @date 2015年12月14日 下午7:12:57
 */
public class Router {
    private static Router router = new Router();
    public Logger logger = LoggerFactory.getLogger(Router.class);
    public AccountMgr accountMgr;
    public LogMgr logMgr;

    private Router() {

    }

    public void initMgr() {
        accountMgr = AccountMgr.getInstance();
        logMgr = LogMgr.getInstance();
    }

    public static Router getInstance() {
        if (null == router) {
            router = new Router();
        }
        return router;
    }

    public void initCsvData() {// 初始化Csv
    }

    public void initData() {// 初始化数值
        accountMgr.initData();
    }

    /**
     * @param ctx void
     * @throws
     * @Title: route
     * @Description: 消息路由分发
     */
    public void route(Short typeid, JSONObject data, Long userid,
                      ChannelHandlerContext ctx) {
        if (userid != null) {
            if (Constants.IP_LIMIT) {
                // 检查重复登录
                // TODO
            }
        }
        switch (typeid) {
            case ProtoIds.TEST:
                accountMgr.test(ctx, data, userid);
                break;
            default:
                HttpInHandler.writeJSON(ctx, ProtoMessage.getErrorResp("协议号错误"));
                logger.error("未注册协议号{}", typeid);
                break;
        }
    }

    public void test(ProtoMessage msg, ChannelHandlerContext ctx) {
    }
}
