package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.District;
import service.data.DataService;
import service.Init;
import service.RequestService;

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
	@Autowired RequestService requestService;
	
	@ModelAttribute
	public void initialize(HttpServletRequest request) {
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
		// if user entered url to get here, use another handler (or redirect back to home)
		requestService.sendHomeIfNotXHR(request, response);

		// if xhr, use this handler
		String selectedState = (String)requestParams.get("code");
		// make sure request params are not null
		if(selectedState != null) {
			// get and return a list of years in which the selected state has available
			return (ArrayList<Integer>)dataService.getDataYearSetByCode(selectedState);
		}
		return null;
	}

	/**
	 * Handles data selection request
	 * @param requestParams
	 * @param request
	 * @param response
	 * @return a list of districts for the selected state in the selected year 
	 */
	@RequestMapping(value="/data", method = RequestMethod.GET)
	public @ResponseBody ArrayList<District> handleGetDataByYear(@RequestParam Map<String,String> requestParams, 
	HttpServletRequest request, HttpServletResponse response) {
		// if user entered url to get here, use another handler (or redirect back to home)
		requestService.sendHomeIfNotXHR(request, response);
		//FIXME: redirect still comes back here 

		// if xhr, use this handler
		String selectedState = (String)requestParams.get("code");
		String selectedYear_Str = requestParams.get("year");
		// make sure request params are not null
		if(selectedState != null && selectedYear_Str != null) {
			// get and return a list of districts
			return (ArrayList<District>)dataService.getDataByYear(selectedState, Integer.parseInt(selectedYear_Str));
		}
		return null;
	}

	/**
	 * Goes to registration page
	 * @return name of registration page
	 */
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("registration");
	}
	/**
	 * Goes to compare page
	 * @return name of compare page
	 */
	@RequestMapping(value="/compare", method = RequestMethod.GET)
	public ModelAndView compare() {
		return new ModelAndView("compare");
	}
	/**
	 * Goes to credit page
	 * @return name of credit page
	 */
	@RequestMapping(value="/credit", method = RequestMethod.GET)
	public ModelAndView creditPage() {
		return new ModelAndView("credit");
	}
	/**
	 * Goes to help page
	 * @return name of help page
	 */
	@RequestMapping(value="/help", method = RequestMethod.GET)
	public ModelAndView helpPage() {
		return new ModelAndView("help");
	}

}