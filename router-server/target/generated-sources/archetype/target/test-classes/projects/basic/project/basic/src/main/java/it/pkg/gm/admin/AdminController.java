package it.pkg.gm.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	public Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(HttpServletRequest request, Model model) {
		model.addAttribute("adminuser",
				request.getSession().getAttribute("admin"));
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
			request.getSession().setMaxInactiveInterval(-1); // Session的有效时间
			return "success";
		}
		logger.error("[{}:{}] gm login err", name, pwd);
		return "failed";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("admin");
		try {
			response.sendRedirect("login");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
