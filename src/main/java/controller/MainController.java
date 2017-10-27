package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.Init;

/**
 * @Author Jia Sheng Ma (jiasheng.ma@yahoo.com)
 */
@Controller
@ControllerAdvice
public class MainController {

	@Autowired
	private Init init;
	
	/**
	 *
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView initialize() {
		ModelAndView home = new ModelAndView("index");
		// read config file, load UI components
		System.out.println("\n************************************\n");
		init.init();

		//TODO: add attributes/object to modelAndView

		return home;
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("registration");
	}
	@RequestMapping(value="/compare", method = RequestMethod.GET)
	public ModelAndView compare() {
		System.out.println("\n************************************\n");
		init.init();

		return new ModelAndView("compare");
	}
	@RequestMapping(value="/credit", method = RequestMethod.GET)
	public ModelAndView creditPage() {
		return new ModelAndView("credit");
	}
	@RequestMapping(value="/help", method = RequestMethod.GET)
	public ModelAndView helpPage() {
		return new ModelAndView("help");
	}

	
	
	
}
