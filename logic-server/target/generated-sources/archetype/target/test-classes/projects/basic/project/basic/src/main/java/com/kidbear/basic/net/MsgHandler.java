package com.kidbear.basic.net;

import com.alibaba.fastjson.JSONObject;
import com.kidbear.basic.core.Router;
import com.kidbear.basic.net.http.HttpInHandler;
import com.kidbear.basic.task.ExecutorPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 何金成
 * @ClassName: MsgHandler
 * @Description: 消息处理器
 */
public class MsgHandler {
    public static Logger logger = LoggerFactory.getLogger(MsgHandler.class);
    public static MsgHandler handler;
    private Message message;

    public static MsgHandler getInstance() {
        return handler == null ? new MsgHandler() : handler;
    }

    protected MsgHandler() {

    }

    public void handle(final Message message) {
        this.message = message;
        /** work线程的内容转交线程池处理，缩短work线程耗时 **/
        ExecutorPool.channelHandleThread.execute(new Runnable() {
            @Override
            public void run() {
                Short typeid = message.msg.getTypeid();
                if (typeid == null) {
                    logger.error("没有typeid");
                    HttpInHandler.writeJSON(message.ctx,
                            ProtoMessage.getErrorResp("没有typeid"));
                    return;
                }
                JSONObject msgData = message.msg.getData();
                Router.getInstance().route(typeid, msgData,
                        message.msg.getUserid(), message.ctx);
            }
        });
    }
}
