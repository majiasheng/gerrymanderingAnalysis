package controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	 * 
	 * @param request
	 * @return
	 */
	// @RequestMapping(value="/state", method = RequestMethod.GET) // e.g. /state?code=NY
	// public ModelAndView handlesSelectState(@RequestParam Map<String,String> requestParams, HttpServletRequest request) {
	// 	ModelAndView mv = new ModelAndView("index");
	// 	//FIXME: make sure attribute is not null
	// 	String code = (String)requestParams.get("code");
	// 	//		int selectedYear = Integer.parseInt(requestParams.get("year"));
	// 	// System.out.println("year:" + init.getSelectedYear());
	// 	// System.out.println("year2:" + ((Init)request.getSession().getAttribute("init")).getSelectedYear());
	// 	if(code != null){
	// 		// get a list of years in which the selected state has available
	// 		ArrayList<Integer> dataYearSet = (ArrayList<Integer>)dataService.getDataYearSetByCode(code);
	// 		// add dataYearSet to modelAndView (response model)		
	// 		//FIXME: model not shown in jsp
	// 		mv.addObject("dataYearSet", dataYearSet);
	// 		for(int i : dataYearSet) {
	// 			System.out.println("i: " + i);
	// 		}
	// 		System.out.println("code: " + code);
	// 	}
	// 	return mv;
	// }

	@RequestMapping(value="/state", method = RequestMethod.GET, produces="application/json") // e.g. /state?code=NY
	public @ResponseBody ArrayList<Integer> handlesSelectState(@RequestParam Map<String,String> requestParams, 
	HttpServletRequest request) {
		String code = (String)requestParams.get("code");

		ArrayList<Integer> dataYearSet = null;
		if(code != null && !code.equals("")){
			// get a list of years in which the selected state has available
			dataYearSet = (ArrayList<Integer>)dataService.getDataYearSetByCode(code);
			for(int i : dataYearSet) {
				System.out.println("i: " + i);
			}
			System.out.println("code: " + code);
		} else {
			//FIXME: redirect back to home
			// home();
		}
		return dataYearSet;
	}

	@RequestMapping(value="/data", method = RequestMethod.GET)
	public ModelAndView handleGetDataByYear(@RequestParam Map<String,String> requestParams, HttpServletRequest request) {

		// fetch request param
		//TODO: if there are no request params like the following, go back to index
		String selectedState = (String)requestParams.get("code");
		int selectedYear = Integer.parseInt(requestParams.get("year"));
		// ArrayList<District> districts = (ArrayList<District>)dataService.getDataByYear(selectedState, selectedYear);
		// State state = (State)request.getSession().getAttribute("selectedState");
		// save current state object to session
		// request.getSession().setAttribute("selectedState", );


		init.setSelectedState(selectedState);
		init.setSelectedYear(selectedYear);
		
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
