package controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import model.District;
import model.State;
import service.DataService;
import service.DataServiceJPAImpl;
import service.Init;

/**
 * @Author Jia Sheng Ma (jiasheng.ma@yahoo.com)
 */
@Controller
@ControllerAdvice
public class MainController {

	@Autowired
	private Init init;
	
	@ModelAttribute
	public void init(HttpServletRequest request) {
		// if session varialbe doesnt have init object, add
		if(request.getSession().getAttribute("init")==null) {
			System.out.println("\n>> Initializing...\n");
			// read config file, load UI components
			init.init();
			request.getSession().setAttribute("init", init);
		}
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView initialize() {
		return  new ModelAndView("index");
	}
	
	@RequestMapping(value="/{state}/{year}", method = RequestMethod.GET)
	public ModelAndView handleGetDataByYear(@RequestParam Map<String,String> requestParams, HttpServletRequest request) {
		// fetch data 
		int selectedState = Integer.parseInt(requestParams.get("stateId"));
		int selectedYear = Integer.parseInt(requestParams.get("year"));
		// ArrayList<District> districts = (ArrayList<District>)dataService.getDataByYear(selectedState, selectedYear);
		// State state = (State)request.getSession().getAttribute("selectedState");
		// save current state object to session
		// request.getSession().setAttribute("selectedState", );
		
		return new ModelAndView("/");
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
