#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.gm;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ${package}.util.memcached.MemcachedCRUD;

@Controller
@RequestMapping(value = "/admin")
public class IndexController {
	public Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = "/index")
	public void index(HttpServletRequest request, Model model) {
		String host = request.getRemoteHost();
		logger.info("{} visit manager server", host);
		String startTime = null;
		if (MemcachedCRUD.getInstance().exist("ServerStartTimePay")) {
			startTime = (String) MemcachedCRUD.getInstance().getObject(
					"ServerStartTimePay");
		} else {
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			startTime = formatter.format(currentTime);
			MemcachedCRUD.getInstance().saveObject("ServerStartTimePay",
					startTime);
		}
		model.addAttribute("startTime", startTime);
		model.addAttribute("adminuser",
				request.getSession().getAttribute("admin"));
	}
}
