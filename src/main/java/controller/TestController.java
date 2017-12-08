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
import model.SessionConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import service.data.DataService;
import service.gerrymandering.GerrymanderingTestService;

/**
 * @Author Jia Sheng Ma (jiasheng.ma@yahoo.com)
 */
@Controller
@ControllerAdvice
public class TestController {

    @Autowired
    private DataService dataService;

    @Autowired
    @Qualifier("TTest")
    private GerrymanderingTestService tTestService;

    @Autowired
    @Qualifier("MMTest")
    private GerrymanderingTestService mmTestService;

    @Autowired
    @Qualifier("EGTest")
    private GerrymanderingTestService egTestService;

    @Autowired
    @Qualifier("CompactnessTest")
    private GerrymanderingTestService compactnessTestService;

    @RequestMapping(value = "/measure/{measureName}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public TestResult handleDoTest(
            @PathVariable(value = "measureName") String measureName,
            HttpServletRequest request) {

        TestResult result = null;
        State state = (State) request.getSession().getAttribute(SessionConstant.STATE_ATTRIBUTE);

        if ("Efficiency Gap Test".equals(measureName)) {
            result = egTestService.doTest(state);
        } else if ("T-Test".equals(measureName)) {
            result = tTestService.doTest(state);
        } else if ("Mean-Median Test".equals(measureName)) {
            result = mmTestService.doTest(state);
        } else if ("Compactness Test".equals(measureName)) {
            result = compactnessTestService.doTest(state);
        } else {

        }

        return result;
    }

    @RequestMapping(value = "/view-measure/{measureName}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public TestResult handleDoTest(
            @PathVariable(value = "measureName") String measureName,
            @RequestParam("state") String selectedState,
            @RequestParam("year") int year,
            HttpServletRequest request) {

        TestResult result = null;

        //TODO: get populate state object
        State state = dataService.getStateByYear(selectedState, year);

        if (state != null) {
            if ("Efficiency Gap Test".equals(measureName)) {
                result = egTestService.doTest(state);
            } else if ("T-Test".equals(measureName)) {
                result = tTestService.doTest(state);
            } else if ("Mean-Median Test".equals(measureName)) {
                result = mmTestService.doTest(state);
            } else if ("Compactness Test".equals(measureName)) {
                result = compactnessTestService.doTest(state);
            }
        }

        return result;
    }
}
