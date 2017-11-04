package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.District;
import model.State;
import service.DataService;
import service.Init;

/**
 * @Author Jia Sheng Ma (jiasheng.ma@yahoo.com)
 */
@Controller
@ControllerAdvice
public class MainController {

	@Autowired
	private Init init;
	@Autowired
	private DataService dataService;
	
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
	public ModelAndView home() {
		return new ModelAndView("index");
	}
	
	/**
	 * Handles state selection request
	 * @param requestParams
	 * @param response
	 * @return a list of available data year set to populate the "Data" drop down menu
	 */
	@RequestMapping(value="/state", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody ArrayList<Integer> handlesSelectState(@RequestParam Map<String,String> requestParams, 
	HttpServletRequest request, HttpServletResponse response) {
		// if xhr, use this handler, if user entered url get, use another handler
		if(!isAjaxRequest(request)) {
			// TODO: redirect to another handler?
			try { // redirect back to home
				response.sendRedirect("/");
			} catch (IOException ex) {
				Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		ArrayList<Integer> dataYearSet = null;
		String code = (String)requestParams.get("code");
		
		if(code != null && !code.equals("")){
			// get a list of years in which the selected state has available
			dataYearSet = (ArrayList<Integer>)dataService.getDataYearSetByCode(code);
		} else {
			
		}
		return dataYearSet;
	}

	@RequestMapping(value="/data", method = RequestMethod.GET)
	public @ResponseBody ArrayList<District> handleGetDataByYear(@RequestParam Map<String,String> requestParams, HttpServletRequest request) {
		// if xhr, use this handler, if user entered url get, use another handler
		if(!isAjaxRequest(request)) {
			// TODO: redirect to another handler?
		}
		ArrayList<District> districts = null; 
		// fetch request param
		String selectedState = (String)requestParams.get("code");
		int selectedYear = Integer.parseInt(requestParams.get("year"));

		districts = (ArrayList<District>)dataService.getDataByYear(selectedState, selectedYear);
		// init.setSelectedYear(selectedYear);
		return districts;
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

	/**
	 * Determines if a request is an AJAX request
	 * @param request incoming request
	 * @return true if the request is an AJAX request, false otherwise
	 */
	private boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("X-Requested-With");
		return "XMLHttpRequest".equals(requestedWith);
	}

}
