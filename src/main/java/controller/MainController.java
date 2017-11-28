package controller;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletContext;

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
    @Autowired
    private ServletContext servletContext;


    /**
     * Initializes the application with configurations,
     * and saves config to session.
     * @param request any incoming http request
     */
    @ModelAttribute
    public void initialize(HttpServletRequest request) {
        // if application scope doesnt have config object, add
        if (servletContext.getAttribute(SessionConstant.CONFIG_ATTRIBUTE)==null) {
            init.init();
            // add to servlet context
            servletContext.setAttribute(SessionConstant.CONFIG_ATTRIBUTE, init.getConfig());
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
        // get selected state from request
        String selectedState = (String) requestParams.get(SessionConstant.STATE_REQUEST_PARAM);

        // make sure request params are not null
        if (selectedState != null) {
            // get and return a list of years in which the selected state has available in db
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

        // get selected state and year as strings from request
        String selectedState = requestParams.get(SessionConstant.STATE_REQUEST_PARAM);
        String selectedYear_str = requestParams.get(SessionConstant.YEAR_REQUEST_PARAM);

        // make sure request params are not null
        if (selectedState != null && selectedYear_str != null) {
            // save state object to session for later use in gerrymandering tests
            State state = dataService.getStateByYear(selectedState, Integer.parseInt(selectedYear_str));
            request.getSession().setAttribute(SessionConstant.STATE_ATTRIBUTE, state);

            // convert districts to JSON
            String distGeoJson = "{\"distGeoJson\":";
            distGeoJson += dataService.districtGeoDataToJson(state.getDistricts());
            distGeoJson += "}";
            return distGeoJson;
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
    public ModelAndView showCredit() {
        return new ModelAndView("credit");
    }

    /**
     * Goes to help page
     *
     * @return name of help page
     */
    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public ModelAndView showHelp() {
        return new ModelAndView("help");
    }

}
