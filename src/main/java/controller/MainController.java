package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		
		// can use binder.setDisallowedFields() to un-bind a property
		
		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

		// use a customized date format for "dateAcquired" request param
		//binder.registerCustomEditor(Date.class, "dateAcquired" ,new CustomDateEditor(simpleDateFormat, false));
		
	}
	
	// @RequestMapping()
//     public ModelAndView initialize() {
//         ModelAndView modelAndView = new ModelAndView("/");
//
//
//
//         return modelAndView;
//     }
	
	
	
}
