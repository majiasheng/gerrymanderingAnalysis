package controller;

import javax.servlet.http.HttpServletRequest;
import model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import model.TestResult;
import service.RequestService;
import service.gerrymandering.GerrymanderingTestService;

/**
 * @Author Jia Sheng Ma (jiasheng.ma@yahoo.com)
 */
@Controller
@ControllerAdvice
public class TestController {

    @Autowired
    @Qualifier("TTest")
    private GerrymanderingTestService tTestService;

    @Autowired
    @Qualifier("MMTest")
    private GerrymanderingTestService mmTestService;

    @Autowired
    @Qualifier("EGTest")
    private GerrymanderingTestService egTestService;

    @RequestMapping(value = "/measure/{measureName}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public TestResult handleDoTest(
            @PathVariable(value = "measureName") String measureName,
            HttpServletRequest request) {

        TestResult result = null;
        State state = (State) request.getSession().getAttribute(RequestService.STATE_ATTRIBUTE);

        if ("Efficiency Gap Test".equals(measureName)) {
            result = tTestService.doTest(state);
        } else if ("T-Test".equals(measureName)) {
            result = mmTestService.doTest(state);
        } else if ("Mean-Median Test".equals(measureName)) {
            result = egTestService.doTest(state);
        }

        // TEST
        // System.out.println("/measure/" + measureName);
        // result = new TestResult();
        // result.setpValue(9.0);
        // END TEST
        return result;
    }
}
