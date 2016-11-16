package com.kidbear.logic.gm.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(HttpServletRequest request, Model model) {
	}

	@RequestMapping(value = "/loading", method = RequestMethod.GET)
	public void loading(HttpServletRequest request, Model model) {
	}

	@RequestMapping(value = "/loginHandle", method = RequestMethod.POST)
	@ResponseBody
	public String loginHandle(HttpServletRequest request) {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		Admin admin = new Admin();
		admin.setName(name);
		admin.setPwd(pwd);
		boolean flag = adminService.login(admin);
		if (flag) {
			request.getSession().setAttribute("admin", admin);
			request.getSession().setMaxInactiveInterval(-1);
			return "success";
		}
		return "failed";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("admin");
		try {
			response.sendRedirect("../admin/login");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
