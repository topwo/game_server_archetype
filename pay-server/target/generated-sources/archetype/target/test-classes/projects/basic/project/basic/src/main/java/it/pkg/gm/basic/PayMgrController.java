package it.pkg.gm.basic;

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

import it.pkg.client.basic.Pay;
import it.pkg.util.cache.MC;
import it.pkg.util.hibernate.HibernateUtil;
import it.pkg.util.hibernate.TableIDCreator;

@Controller
@RequestMapping(value = "/basicmgr")
public class PayMgrController {
	public Logger logger = LoggerFactory.getLogger(PayMgrController.class);

	@RequestMapping(value = "/basiclist")
	public void basic(HttpServletRequest request, Model model) {
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
		List<Pay> basicList = null;
		basicList = flag ? HibernateUtil.list(Pay.class, whereBuffer.toString())
				: Collections.EMPTY_LIST;
		model.addAttribute("basicList", basicList);
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
		String basicDate = request.getParameter("basicDate");
		String isFinished = request.getParameter("isFinished");
		String orderid = request.getParameter("orderid");
		String userid = request.getParameter("userid");
		// String zone = request.getParameter("zone");
		try {
			Pay basic = new Pay();
			basic.setAccount(account);
			basic.setAmount(Double.parseDouble(amount));
			// basic.setAppid(Long.parseLong(appid));
			basic.setBillno(TableIDCreator.getTableID(Pay.class, 1));
			basic.setChannel(channel);
			basic.setGamename(gamename);
			basic.setGoodType(Integer.parseInt(goodType));
			basic.setIsFinished(Integer.parseInt(isFinished));
			basic.setOrderid(orderid);
			basic.setPayDate(new Date(
					Integer.parseInt(basicDate.split("-")[0]) - 1900, Integer
							.parseInt(basicDate.split("-")[1]) - 1, Integer
							.parseInt(basicDate.split("-")[2])));
			basic.setUserid(Long.parseLong(userid));
			// basic.setZone(Integer.parseInt(zone));
			MC.add(basic, basic.getIdentifier() + "");
			HibernateUtil.insert(basic, basic.getBillno());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
}
