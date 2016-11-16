package com.kidbear.pay.gm.pay;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kidbear.pay.client.pay.Pay;
import com.kidbear.pay.util.cache.MC;
import com.kidbear.pay.util.hibernate.HibernateUtil;
import com.kidbear.pay.util.hibernate.TableIDCreator;

@Controller
@RequestMapping(value = "/paymgr")
public class PayMgrController {
	public Logger logger = LoggerFactory.getLogger(PayMgrController.class);

	@RequestMapping(value = "/paylist")
	public void pay(HttpServletRequest request, Model model) {
		String orderId = request.getParameter("orderId");
		String userId = request.getParameter("userId");
		String billno = request.getParameter("billno");
		StringBuffer whereBuffer = new StringBuffer("where 1=1");
		boolean flag = false;
		if (orderId != null && orderId.length() > 0) {
			whereBuffer.append(" and orderid='" + orderId + "'");
			model.addAttribute("orderId", orderId);
			flag = true;
		}
		if (userId != null && userId.length() > 0) {
			whereBuffer.append(" and userid=" + userId + "");
			model.addAttribute("userId", userId);
			flag = true;
		}
		if (billno != null && billno.length() > 0) {
			whereBuffer.append(" and billno=" + billno + "");
			model.addAttribute("billno", billno);
			flag = true;
		}
		List<Pay> payList = null;
		payList = flag ? HibernateUtil.list(Pay.class, whereBuffer.toString())
				: Collections.EMPTY_LIST;
		model.addAttribute("payList", payList);
	}

	@RequestMapping(value = "/addPay")
	public void addPay() {
	}

	@RequestMapping(value = "/addPayHandle", method = RequestMethod.POST)
	@ResponseBody
	public String addPayHandle(HttpServletRequest request) {
		String account = request.getParameter("account");
		String amount = request.getParameter("amount");
		// String appid = request.getParameter("appid");
		String channel = request.getParameter("channel");
		String gamename = request.getParameter("gamename");
		String goodType = request.getParameter("goodType");
		String payDate = request.getParameter("payDate");
		String isFinished = request.getParameter("isFinished");
		String orderid = request.getParameter("orderid");
		String userid = request.getParameter("userid");
		// String zone = request.getParameter("zone");
		try {
			Pay pay = new Pay();
			pay.setAccount(account);
			pay.setAmount(Double.parseDouble(amount));
			// pay.setAppid(Long.parseLong(appid));
			pay.setBillno(TableIDCreator.getTableID(Pay.class, 1));
			pay.setChannel(channel);
			pay.setGamename(gamename);
			pay.setGoodType(Integer.parseInt(goodType));
			pay.setIsFinished(Integer.parseInt(isFinished));
			pay.setOrderid(orderid);
			pay.setPayDate(new Date(
					Integer.parseInt(payDate.split("-")[0]) - 1900, Integer
							.parseInt(payDate.split("-")[1]) - 1, Integer
							.parseInt(payDate.split("-")[2])));
			pay.setUserid(Long.parseLong(userid));
			// pay.setZone(Integer.parseInt(zone));
			MC.add(pay, pay.getIdentifier() + "");
			HibernateUtil.insert(pay, pay.getBillno());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
}
