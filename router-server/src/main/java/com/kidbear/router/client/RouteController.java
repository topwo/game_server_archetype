package com.kidbear.router.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kidbear.router.client.model.Account;
import com.kidbear.router.client.service.AccountService;
import com.kidbear.router.core.GameInit;
import com.kidbear.router.gm.server.ServerConfig;
import com.kidbear.router.util.Constants;
import com.kidbear.router.util.hibernate.HibernateUtil;
import com.kidbear.router.util.redis.Redis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/route")
public class RouteController {
    @Autowired
    private AccountService accountService;
    public Logger logger = LoggerFactory.getLogger(RouteController.class);

    public RouteController() {
    }

    @RequestMapping(value = "/loginOrRegist", method = RequestMethod.POST)
    public void loginOrRegist(HttpServletRequest request,
                              HttpServletResponse response) {
        String username = request.getParameter("name");
        String password = request.getParameter("pwd");
        String channel = request.getParameter("channel");
        int channelCode = (channel == null || channel.length() == 0) ? ChannelCode.NONE
                : Integer.parseInt(channel);
        JSONObject ret = new JSONObject();
        int state = 0;
        if (username == null && password == null) {
            // 一键登录
            JSONArray oneKeyArr = accountService.oneKeyLogin(channelCode);
            username = oneKeyArr.getString(0);
            state = 200;
            ret.put("code", state);
            ret.put("msg", "一键登录成功");
            ret.put("name", oneKeyArr.getString(0));
            ret.put("pwd", oneKeyArr.getString(1));
        } else if (username == null) {
            ret.put("code", 1);
            ret.put("msg", "用户名不能为空");
            writeJSON(ret, response, request.getRemoteAddr());
            return;
        } else if (password == null) {
            ret.put("code", 1);
            ret.put("msg", "密码不能为空");
            writeJSON(ret, response, request.getRemoteAddr());
            return;
        } else {
            // 正常登陆
            username = username.trim();
            password = password.trim();
            state = accountService.loginOrRegist(username, password,
                    channelCode);
            ret.put("code", state);
            switch (state) {
                case 100:
                    ret.put("msg", "登录成功");
                    break;
                case 101:
                    ret.put("msg", "登录密码错误");
                    break;
                case 200:
                    ret.put("msg", "注册成功");
                    break;
                case 201:
                    ret.put("msg", "请输入6至10位密码");
                    break;
                case 202:
                    ret.put("msg", "请使用字母或者数字");
                    break;
                case 203:
                    ret.put("msg", "用户名不能超过8个字");
                    break;
            }
        }
        if (state == 100 || state == 200) {
            Account account = accountService.getAccount(username);
            ret.put("userid", account.getId());
            ret.put("lastserver", account.getLastServer());
            JSONArray servers = getServerList(ret, account);
            ret.put("server", servers);
        }
        writeJSON(ret, response, request.getRemoteAddr());
    }

    private JSONArray getServerList(JSONObject ret, Account account) {
        List<ServerConfig> serverConfigs = HibernateUtil.list(
                ServerConfig.class, " order by id DESC");
        JSONArray servers = new JSONArray();
        for (ServerConfig serverConfig : serverConfigs) {
            if (serverConfig.getWhiteMode() == 1) {
                // 白名单过滤
                if (Arrays.asList(serverConfig.getWhiteUsers().split(","))
                        .indexOf(String.valueOf(account.getName())) == -1
                        && Arrays.asList(
                        serverConfig.getWhiteUsers().split(","))
                        .indexOf(String.valueOf(account.getId())) == -1) {
                    if (serverConfig.getId() == account.getLastServer()) {
                        // 最近登录服务器
                        ret.put("lastserver", 0);
                    }
                    continue;
                }
            }
            // 渠道过滤
            if (serverConfig.getOpenChannels() == null) {
                continue;
            }
            if (Arrays.asList(serverConfig.getOpenChannels().split(","))
                    .indexOf(String.valueOf(account.getChannel())) == -1) {
                if (serverConfig.getId() == account.getLastServer()) {
                    // 最近登录服务器
                    ret.put("lastserver", 0);
                }
                continue;
            }
            JSONArray serverArr = new JSONArray();
            serverArr.add(serverConfig.getId());
            serverArr.add(serverConfig.getIp());
            serverArr.add(serverConfig.getPort());
            serverArr.add(serverConfig.getState());
            serverArr.add(serverConfig.getName());
            servers.add(serverArr);
        }
        return servers;
    }

    @RequestMapping(value = "/queryDownUrl", method = RequestMethod.GET)
    public void queryDownUrl(HttpServletRequest request,
                             HttpServletResponse response) {
        logger.info("queryDownUrl:{}",
                JSON.toJSONString(request.getParameterMap()));
        JSONArray ret = new JSONArray();
        String version = request.getParameter("fileversion");// 版本号
        String base = request.getParameter("base");// 版本号
        String channel = request.getParameter("channel");// 渠道号
        // TODO 获取版本号val
        String val = "1.0.0#0";// get from redis or mysql
        String newBase = Redis.getInstance()
                .ztop(Redis.GLOBAL_DB, GameInit.HOT_FIX_BASE + channel, 1)
                .iterator().next().split("#")[1];
        val = val == null ? "1.0.0#0" : val;
        version = version == null ? "1.0.0#0" : version;
        newBase = newBase == null ? "1.0" : newBase;
        int baseInt = Integer.parseInt(base.split("\\.")[0]) * 10
                + Integer.parseInt(base.split("\\.")[1]);
        int newBaseInt = Integer.parseInt(newBase.split("\\.")[0]) * 10
                + Integer.parseInt(newBase.split("\\.")[1]);
        int versionInt = Integer.parseInt(version.split("\\.")[0]) * 100
                + Integer.parseInt(version.split("\\.")[1]) * 10
                + Integer.parseInt(version.split("\\.")[2]);
        int newVersionInt = Integer.parseInt(val.split("#")[0].split("\\.")[0])
                * 100 + Integer.parseInt(val.split("#")[0].split("\\.")[1])
                * 10 + Integer.parseInt(val.split("#")[0].split("\\.")[2]);
        if (baseInt > newBaseInt) {
            ret.add(0);
        } else if (baseInt < newBaseInt) {
            // 大版本更新
            ret.add(newBase);
        } else {
            if (versionInt > newVersionInt) {
                ret.add(0);
            } else if (versionInt < newVersionInt) {
                // 小版本更新
                // 下载地址
                ret.add("http://" + GameInit.fileIp + ":" + GameInit.filePort);
                // 文件大小
                ret.add(val.split("#")[1]);
                // 更新版本号
                ret.add(val.split("#")[0]);
            } else {
                ret.add(0);
            }
        }
        writeJSON(ret, response, request.getRemoteAddr());
    }

    @RequestMapping(value = "/queryVersion", method = RequestMethod.GET)
    public void queryVersion(HttpServletRequest request,
                             HttpServletResponse response) {
        String channel = request.getParameter("channel");
        String base = request.getParameter("base");
        // TODO 获取版本号val
        String val = "1.0.0#0";// get from redis or mysql
        if (val == null) {
            writeJSON("0.0.0", response, request.getRemoteAddr());
        } else {
            writeJSON(val.split("#")[0], response, request.getRemoteAddr());
        }
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public void regist(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("name");
        String password = request.getParameter("pwd");
        JSONArray ret = new JSONArray();
        if (username == null) {
            ret.add(1);
            ret.add("用户名不能为空");
            writeJSON(ret, response, request.getRemoteAddr());
            return;
        }
        if (password == null) {
            ret.add(1);
            ret.add("密码不能为空");
            writeJSON(ret, response, request.getRemoteAddr());
            return;
        }
        username = username.trim();
        password = password.trim();
        int state = accountService.regist(username, password, 0);
        switch (state) {
            case 0:
                ret.add(0);
                ret.add("注册成功");
                break;
            case 1:
                ret.add(1);
                ret.add("账号已存在");
                break;
            case 2:
                ret.add(2);
                ret.add("密码至少8位数");
                break;
        }
        writeJSON(ret, response, request.getRemoteAddr());
        return;
    }

    @RequestMapping(value = "/querynotice", method = RequestMethod.GET)
    public void queryNotice(HttpServletRequest request,
                            HttpServletResponse response) {
        String notice = "";
        JSONArray ret = new JSONArray();
        ret.add(notice);
        writeJSON(ret, response, request.getRemoteAddr());
    }

    @RequestMapping(value = "/chooseServer", method = RequestMethod.POST)
    public void chooseServer(HttpServletRequest request,
                             HttpServletResponse response) {
        long userid = Long.parseLong(request.getParameter("userid"));
        long server = Long.parseLong(request.getParameter("server"));
        Account account = HibernateUtil.find(Account.class,
                (userid - server) / 1000);
        if (account != null) {
            account.setLastServer(server);
            HibernateUtil.save(account);
        }
        JSONObject result = new JSONObject();
        // TODO
        writeJSON(result, response, request.getRemoteAddr());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("name");
        String password = request.getParameter("pwd");
        JSONArray ret = new JSONArray();
        if (username == null) {
            ret.add(1);
            ret.add("用户名不能为空");
            writeJSON(ret, response, request.getRemoteAddr());
            return;
        }
        if (password == null) {
            ret.add(1);
            ret.add("密码不能为空");
            writeJSON(ret, response, request.getRemoteAddr());
            return;
        }
        username = username.trim();
        password = password.trim();
        int state = accountService.login(username, password, 0);
        switch (state) {
            case 0:
                ret.add(0);
                ret.add("登录成功");
                break;
            case 1:
                ret.add(1);
                ret.add("账号不存在");
                break;
            case 2:
                ret.add(2);
                ret.add("密码错误");
                break;
        }
        writeJSON(ret, response, request.getRemoteAddr());
    }

    @RequestMapping(value = "/getServers")
    public void getServers(HttpServletRequest request,
                           HttpServletResponse response) {
        List<ServerConfig> serverConfigs = HibernateUtil.list(
                ServerConfig.class, "");
        JSONArray ret = new JSONArray();
        for (ServerConfig serverConfig : serverConfigs) {
            JSONArray serverArr = new JSONArray();
            serverArr.add(serverConfig.getIp());
            serverArr.add(serverConfig.getPort());
            serverArr.add(serverConfig.getState());
            serverArr.add(serverConfig.getName());
            ret.add(serverArr);
        }
        writeJSON(ret, response, request.getRemoteAddr());
    }

    @RequestMapping(value = "/getServerConfig")
    public void getServerConfig(HttpServletRequest request,
                                HttpServletResponse response) {
        String serverid = request.getParameter("serverid");
        ServerConfig serverConfig = HibernateUtil.find(ServerConfig.class,
                Integer.parseInt(serverid));
        JSONArray ret = new JSONArray();
        ret.add(serverConfig.getIp());
        ret.add(serverConfig.getTomcatPort());
        writeJSON(ret, response, request.getRemoteAddr());
    }

    protected void writeJSON(Object msg, HttpServletResponse response,
                             String remoteAddr) {
        String result = (msg instanceof String) ? (String) msg : JSON
                .toJSONString(msg);
        if (Constants.CLIENT_DEBUG) {
            logger.info("ip:{},write:{}", remoteAddr, result);
        }
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("{}", e);
        }
    }
}
