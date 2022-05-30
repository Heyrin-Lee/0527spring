package co.micol.prj;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET) //root일때도
	public String home(Locale locale, Model model) {
		
		return "home/home";
	}
	
	@RequestMapping("/home.do") //home.do일때도
	public String homeDo() {
		return "home/home"; //home.jsp로 가도록한다
	}
}
