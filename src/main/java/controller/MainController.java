package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author Jia Sheng Ma (jiasheng.ma@yahoo.com)
 */
@Controller
@ControllerAdvice
public class MainController {
	
	/**
	 * 
	 * @param binder
	 */
	// @InitBinder
	// public void InitBinder(WebDataBinder binder) {
		
	// 	// can use binder.setDisallowedFields() to un-bind a property
		
	// 	// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

	// 	// use a customized date format for "dateAcquired" request param
	// 	//binder.registerCustomEditor(Date.class, "dateAcquired" ,new CustomDateEditor(simpleDateFormat, false));
		
	// }
	
	@RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView initialize() {
        ModelAndView modelAndView = new ModelAndView("index");



        return modelAndView;
    }
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("registration");
	}

	@RequestMapping(value="/compare", method = RequestMethod.GET)
	public ModelAndView compare() {
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
