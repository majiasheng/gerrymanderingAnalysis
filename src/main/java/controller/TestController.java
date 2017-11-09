package controller;

import javax.servlet.http.HttpServletRequest;
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
    // private GerrymanderingTestService tTestService;
    // @Autowired
    // @Qualifier("mmTest")
    // private GerrymanderingTestService mmTestService;
    // @Autowired
    // @Qualifier("egTest")
    // private GerrymanderingTestService egTestService;
    @RequestMapping(value = "/measure/{measureName}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    TestResult handleDoTest(@PathVariable(value = "measureName") String measureName, HttpServletRequest request) {
        TestResult result = null;
        if ("Efficiency Gap Test".equals(measureName)) {
            // result = tTestService.doTest();
        } else if ("T-Test".equals(measureName)) {
            // result = mmTestService.doTest();
        } else if ("Mean-Median Test".equals(measureName)) {
            // result = egTestService.doTest();
        }

		//TEST
        // System.out.println("/measure/" + measureName);
        // result = new TestResult();
        // result.setpValue(9.0);
        // END TEST
        return result;
    }
}
