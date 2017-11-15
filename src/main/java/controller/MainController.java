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

import model.District;
import model.GeoData;
import model.State;
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
    @Autowired
    private RequestService requestService;

    /**
     * Initializes the application with configurations, and save to session.
     *
     * @param request any incoming http request
     */
    @ModelAttribute
    public void initialize(HttpServletRequest request) {
        // if session varialbe doesnt have init object, add
        if (request.getSession().getAttribute("init") == null) {
            System.out.println("\n>> Initializing...\n");
            // read config file, load UI components
            init.init();
            request.getSession().setAttribute("init", init);
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

        // if user entered url to get here, use another handler (or redirect back to home)
        boolean sentHome = requestService.sendHomeIfNotXHR(request, response);

        // if xhr, use this handler
        String selectedState = (String) requestParams.get("code");
        // make sure request params are not null
        if (selectedState != null && !sentHome) {
            // get and return a list of years in which the selected state has available
            return (ArrayList<Integer>) dataService.getDataYearSetByCode(selectedState);
        }
        return null;
    }

    /**
     * Convert list of districts into json
     *
     * @param districts list of districts
     * @return json string
     */
    private String districtsToJson(ArrayList<District> districts){
      // Turn list to string
      String geojsonStr = "{\"type\":\"FeatureCollection\",\"features\":[";
      String geojsonStrEnd = "]}";
      // guard against empty list
      boolean ran = false;
      for (District district : districts) {
          ran = true;
          geojsonStr += district.getGeoData().getBoundary() + ",";
      }
      // remove the extra comma
      if (ran) {
        geojsonStr = geojsonStr.substring(0, geojsonStr.length()-1);
      }
      return geojsonStr + geojsonStrEnd;
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

        // if user entered url to get here, use another handler (or redirect back to home)
        boolean sentHome =false;//= requestService.sendHomeIfNotXHR(request, response);

        // if xhr, use this handler
        String selectedState = (String) requestParams.get("code");
        String selectedYear_str = requestParams.get("year");
        // make sure request params are not null
        if (selectedState != null && selectedYear_str != null && !sentHome) {
            // get and return a list of districts
            int selectedYear = Integer.parseInt(selectedYear_str);
            ArrayList<District> districts = (ArrayList<District>) dataService.getDataByYear(selectedState, selectedYear);
            State state = new State(selectedYear, selectedState, districts);
            // save state object to session for later use in gerrymandering tests
            request.getSession().setAttribute(RequestService.STATE_ATTRIBUTE, state);

            // return districts;
            // make some dummy data
            ArrayList<District> sampleDistricts = new ArrayList<District>();
            District randomDist = new District();
            randomDist.setGeoData(new GeoData());
            randomDist.getGeoData().setBoundary("{\"type\":\"Feature\",\"properties\":{\"test\":\"one\",\"test2\":\"one\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[-73.32275390625,44.91035917458495],[-76.4208984375,43.41302868475145],[-78.92578124999999,43.23719944365308],[-79.73876953125,42.01665183556825],[-75.41015624999999,42.10637370579324],[-74.454345703125,41.343824581185686],[-73.597412109375,41.42625319507269],[-73.32275390625,44.91035917458495]]]}}");
            District randomDist2 = new District();
            randomDist2.setGeoData(new GeoData());
            randomDist2.getGeoData().setBoundary("{\"type\":\"Feature\",\"properties\":{\"test\":\"two\",\"test2\":\"two\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[-74.454345703125,41.335575973123916],[-73.90228271484375,41.017210578228436],[-74.25384521484375,40.50126945841645],[-71.8505859375,41.07935114946899],[-73.6029052734375,41.413895564677304],[-74.454345703125,41.335575973123916]]]}}");
            sampleDistricts.add(randomDist);
            sampleDistricts.add(randomDist2);

            // convert districts to JSON
            return districtsToJson(sampleDistricts);
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
