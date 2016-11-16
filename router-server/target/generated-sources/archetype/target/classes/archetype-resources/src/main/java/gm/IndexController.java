#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.gm;

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
//		String startTime = null;
//		if (MemcachedCRUD.getInstance().exist("ServerStartTimeRouter")) {
//			startTime = (String) MemcachedCRUD.getInstance().getObject(
//					"ServerStartTimeRouter");
//		} else {
//			Date currentTime = new Date();
//			SimpleDateFormat formatter = new SimpleDateFormat(
//					"yyyy-MM-dd HH:mm:ss");
//			startTime = formatter.format(currentTime);
//			MemcachedCRUD.getInstance().saveObject("ServerStartTimeRouter",
//					startTime);
//		}
//		model.addAttribute("startTime", startTime);
		model.addAttribute("adminuser",
				request.getSession().getAttribute("admin"));
	}
}
