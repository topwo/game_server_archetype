package com.kidbear.router.gm.notice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {
    public static final String NOTICE = "notice";

    @RequestMapping(value = "updatenotice")
    public void updateNotice(Model model) {
        String notice = null;
//		notice = Redis.getInstance().exist_(Redis.GLOBAL_DB, NOTICE) ? Redis
//				.getInstance().get(Redis.GLOBAL_DB, NOTICE) : "暂无公告";
        notice = "游戏公告...";
        model.addAttribute("notice", notice);
    }

    @RequestMapping(value = "updateNoticeHandle")
    @ResponseBody
    public String updateNoticeHandle(HttpServletRequest request) {
        String notice = request.getParameter("notice");
        if (notice == null || notice.length() == 0) {
            return "failed";
        }
//		Redis.getInstance().set(Redis.GLOBAL_DB, NOTICE, notice);
        return "success";
    }
}
