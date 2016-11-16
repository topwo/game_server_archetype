package com.kidbear.basic.gm.log;

import com.kidbear.basic.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/log")
public class LogController {

	public static String SDK_DEBUG = "sdk";
	public static String MSG_LOG_DEBUG = "msg";
	public static String CLIENT_DEBUG = "client";
	public static String SAVE_DEBUG = "save";
	public static String READ_DEBUG = "read";
	public static String OPEN_PAY = "pay";

	@RequestMapping(value = "/logConfig")
	public void logConfig(Model model) {
		model.addAttribute(SDK_DEBUG, Constants.SDK_DEBUG);
		model.addAttribute(MSG_LOG_DEBUG, Constants.MSG_LOG_DEBUG);
		model.addAttribute(CLIENT_DEBUG, Constants.CLIENT_DEBUG);
	}

	@RequestMapping(value = "/logConfigHandle", method = RequestMethod.POST)
	@ResponseBody
	public String logConfig(HttpServletRequest request,
			HttpServletResponse response) {
		Boolean sdkDebug = Boolean.valueOf(request.getParameter(SDK_DEBUG));
		Boolean msgLogDebug = Boolean.valueOf(request
				.getParameter(MSG_LOG_DEBUG));
		Boolean clientDebug = Boolean.valueOf(request
				.getParameter(CLIENT_DEBUG));
		Boolean openPayDebug = Boolean.valueOf(request.getParameter(OPEN_PAY));
		Constants.SDK_DEBUG = sdkDebug;
		Constants.MSG_LOG_DEBUG = msgLogDebug;
		Constants.CLIENT_DEBUG = clientDebug;
		return "success";
	}
}
