package controller;

import java.util.ArrayList;
import java.util.Map;

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

import model.State;
import service.data.DataService;
import service.Init;
import model.SessionConstant;

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

    /**
     * Initializes the application with configurations, and save to session.
     *
     * @param request any incoming http request
     */
    @ModelAttribute
    public void initialize(HttpServletRequest request) {
        // if session varialbe doesnt have init object, add
        if (request.getSession().getAttribute(SessionConstant.INIT_ATTRIBUTE) == null) {
            init.init();
            request.getSession().setAttribute(SessionConstant.INIT_ATTRIBUTE, init);
        }
    }

    /**
     * Handles state selection request
     *
     * @param requestParams
     * @param request
     * @param response
     * @return a list of available data year set to populate the "Data" drop
     * down menu
     */
    @RequestMapping(value = "/state", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ArrayList<Integer> handleSelectState(
            @RequestParam Map<String, String> requestParams,
            HttpServletRequest request, HttpServletResponse response) {

        String selectedState = (String) requestParams.get(SessionConstant.STATE_REQUEST_PARAM);
        // make sure request params are not null
        if (selectedState != null) {
            // get and return a list of years in which the selected state has available
            return (ArrayList<Integer>) dataService.getDataYearSetByState(selectedState);
        }
        return null;
    }

    /**
     * Handles data selection request
     *
     * @param requestParams
     * @param request
     * @param response
     * @return a list of districts for the selected state in the selected year
     */
    @RequestMapping(value = "/data", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String handleGetDataByYear(
            @RequestParam Map<String, String> requestParams,
            HttpServletRequest request, HttpServletResponse response) {

        String selectedState = requestParams.get(SessionConstant.STATE_REQUEST_PARAM);
        String selectedYear_str = requestParams.get(SessionConstant.YEAR_REQUEST_PARAM);
        // make sure request params are not null
        if (selectedState != null && selectedYear_str != null) {
            // get and return a list of districts
            int selectedYear = Integer.parseInt(selectedYear_str);

            // save state object to session for later use in gerrymandering tests
            State state = dataService.getStateByYear(selectedState, selectedYear);
            request.getSession().setAttribute(SessionConstant.STATE_ATTRIBUTE, state);

            // convert districts to JSON
            String jsonContainer = "{\"distGeoJson\":";
            jsonContainer += dataService.districtGeoDataToJson(state.getDistricts());
            jsonContainer += "}";
            return jsonContainer;
        }
        return null;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView goHome() {
        return new ModelAndView("index");
    }

    /**
     * Goes to registration page
     *
     * @return name of registration page
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("registration");
    }

    /**
     * Goes to compare page
     *
     * @return name of compare page
     */
    @RequestMapping(value = "/compare", method = RequestMethod.GET)
    public ModelAndView compare() {
        return new ModelAndView("compare");
    }

    /**
     * Goes to credit page
     *
     * @return name of credit page
     */
    @RequestMapping(value = "/credit", method = RequestMethod.GET)
    public ModelAndView creditPage() {
        return new ModelAndView("credit");
    }

    /**
     * Goes to help page
     *
     * @return name of help page
     */
    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public ModelAndView helpPage() {
        return new ModelAndView("help");
    }

}
