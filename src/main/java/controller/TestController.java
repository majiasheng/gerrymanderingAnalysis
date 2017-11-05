package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.GerrymanderingTestService;
import service.TestResult;

/**
 * @Author Jia Sheng Ma (jiasheng.ma@yahoo.com)
 */
@Controller
@ControllerAdvice
@RequestMapping("/measure")
public class TestController {
	
	@Autowired
	GerrymanderingTestService testService;
	
	@RequestMapping(value="/{measureName}",  method = RequestMethod.GET,  produces="application/json")
	public @ResponseBody TestResult handleDoTest(@PathVariable(value="measureName") String measureName) {
		
		System.out.println("/measure/" + measureName);
		return null;
	}
}