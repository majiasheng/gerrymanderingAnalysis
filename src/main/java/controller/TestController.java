package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.gerrymandering.TestResult;


/**
 * @Author Jia Sheng Ma (jiasheng.ma@yahoo.com)
 */
@Controller
@ControllerAdvice
public class TestController {
	
	// @Autowired
	// @Qualifier("tTest")
	// GerrymanderingTestService tTestService;
	// @Autowired
	// @Qualifier("mmTest")
	// GerrymanderingTestService mmTestService;
	// @Autowired
	// @Qualifier("egTest")
	// GerrymanderingTestService egTestService;
	
	@RequestMapping(value="/measure/{measureName}",  method = RequestMethod.GET,  produces="application/json")
	public @ResponseBody TestResult handleDoTest(@PathVariable(value="measureName") String measureName) {
		if("Efficiency Gap Test".equals(measureName)) {
			 // tTestService.doTest();
		} else if("T-Test".equals(measureName)) {
			// mmTestService.doTest();
		} else if("Mean-Median Test".equals(measureName)) {
			// egTestService.doTest();
		}
		System.out.println("/measure/" + measureName);
		return null;
	}
}