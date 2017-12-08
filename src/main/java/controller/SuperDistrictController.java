package controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SessionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.superdistricting.SuperDistrictService;

/**
 *
 * @author majiasheng
 */
@Controller
public class SuperDistrictController {

    @Autowired
    private SuperDistrictService superDistrictService;

    @RequestMapping(value = "/verifySD", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public boolean handleCreateSD(HttpServletRequest request) {

        //TODO: get list of districts
        ArrayList<Integer> districtNumbers = new ArrayList<Integer>();
        Enumeration<String> paramNames = request.getParameterNames();

        String state = null;
        // get all district numbers 
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramName.equals("state")) {
                state = paramName;
            } else {
                for (String s : paramValues) {
                    try {
                        int disNum = Integer.parseInt(s);
                        districtNumbers.add(disNum);
                    } catch (NumberFormatException nfe) {
                        nfe.printStackTrace();
                        System.err.println("NaN");
                    }
                }
            }
        }

        // TODO: get districts by district number and state name
        // return superDistrictService.verifySD();
        return false;
    }

    @RequestMapping(value = "/createSD", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Integer> handleCreateSD(
            @RequestParam Map<String, String> requestParams,
            HttpServletRequest request, HttpServletResponse response) {

        return null;
    }
}
