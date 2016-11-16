#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.kidbear.rpc;

import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.kidbear.${artifactId}.manager.account.AccountMgr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Henry on 2016/11/11.
 */
@WebServlet(name = "TestRpc")
public class TestRpc extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private static final long serialVersionUID = 92071897414733045L;
    /**
     * The Rpc server.
     */
    protected JsonRpcServer rpcServer;
    /**
     * The Logger.
     */
    public Logger logger = LoggerFactory.getLogger(AccountMgr.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        rpcServer.handle(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        // 指定Rpc实现类
        rpcServer = new JsonRpcServer(AccountMgr.getInstance(), AccountMgr.class);
    }
}
