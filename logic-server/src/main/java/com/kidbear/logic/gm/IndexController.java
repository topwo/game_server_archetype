package com.kidbear.logic.gm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin")
public class IndexController {
    public Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/index")
    public void index(HttpServletRequest request, Model model) {
        String host = request.getRemoteHost();
        logger.info("{} visit gm", host);
//        String startTime = null;
//        Date currentTime = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        if (MemcachedCRUD.getInstance().exist(
//                "ServerStartTime" + GameInit.serverId)) {
//            startTime = (String) MemcachedCRUD.getInstance().getObject(
//                    "ServerStartTime" + GameInit.serverId);
//        } else {
//            startTime = formatter.format(currentTime);
//            MemcachedCRUD.getInstance().saveObject(
//                    "ServerStartTime" + GameInit.serverId, startTime);
//        }
//        long startServerTime = Long.parseLong(Redis.getInstance().hget(
//                Redis.GLOBAL_DB, GameInit.serverStartTime,
//                GameInit.serverId + ""));
//        model.addAttribute("startServerTime",
//                formatter.format(new Date(startServerTime)));
//        model.addAttribute("startTime", startTime);
        model.addAttribute("adminuser",
                request.getSession().getAttribute("admin"));
    }
}
