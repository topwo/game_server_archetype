#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.gm.${artifactId};

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

import ${package}.client.${artifactId}.Pay;
import ${package}.util.cache.MC;
import ${package}.util.hibernate.HibernateUtil;
import ${package}.util.hibernate.TableIDCreator;

@Controller
@RequestMapping(value = "/${artifactId}mgr")
public class PayMgrController {
	public Logger logger = LoggerFactory.getLogger(PayMgrController.class);

	@RequestMapping(value = "/${artifactId}list")
	public void ${artifactId}(HttpServletRequest request, Model model) {
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
		List<Pay> ${artifactId}List = null;
		${artifactId}List = flag ? HibernateUtil.list(Pay.class, whereBuffer.toString())
				: Collections.EMPTY_LIST;
		model.addAttribute("${artifactId}List", ${artifactId}List);
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
		String ${artifactId}Date = request.getParameter("${artifactId}Date");
		String isFinished = request.getParameter("isFinished");
		String orderid = request.getParameter("orderid");
		String userid = request.getParameter("userid");
		// String zone = request.getParameter("zone");
		try {
			Pay ${artifactId} = new Pay();
			${artifactId}.setAccount(account);
			${artifactId}.setAmount(Double.parseDouble(amount));
			// ${artifactId}.setAppid(Long.parseLong(appid));
			${artifactId}.setBillno(TableIDCreator.getTableID(Pay.class, 1));
			${artifactId}.setChannel(channel);
			${artifactId}.setGamename(gamename);
			${artifactId}.setGoodType(Integer.parseInt(goodType));
			${artifactId}.setIsFinished(Integer.parseInt(isFinished));
			${artifactId}.setOrderid(orderid);
			${artifactId}.setPayDate(new Date(
					Integer.parseInt(${artifactId}Date.split("-")[0]) - 1900, Integer
							.parseInt(${artifactId}Date.split("-")[1]) - 1, Integer
							.parseInt(${artifactId}Date.split("-")[2])));
			${artifactId}.setUserid(Long.parseLong(userid));
			// ${artifactId}.setZone(Integer.parseInt(zone));
			MC.add(${artifactId}, ${artifactId}.getIdentifier() + "");
			HibernateUtil.insert(${artifactId}, ${artifactId}.getBillno());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
}
