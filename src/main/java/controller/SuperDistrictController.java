package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DistrictDTO;
import model.SessionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import persistence.dao.DataAccessor;
import service.superdistricting.SuperDistrictService;

/**
 *
 * @author majiasheng
 */
@Controller
public class SuperDistrictController {

    @Autowired
    private SuperDistrictService superDistrictService;
    @Autowired
    private DataAccessor dao;

    @RequestMapping(value = "/verifySD", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public boolean handleCreateSD(HttpServletRequest request) {

        //TODO: get list of districts
        ArrayList<Integer> districtNumbers = new ArrayList<Integer>();
        Enumeration<String> paramNames = request.getParameterNames();

        String state = null;
        int year=-1; // set a default
        // get all district numbers 
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            // System.out.println("\n\nDEBUG: " + paramName+ ": " + Arrays.toString(paramValues));
            if (paramName.equals("state") && paramValues.length > 0) {
                state = paramValues[0];
            } else if (paramName.equals("year") && paramValues.length > 0) {
                year = Integer.parseInt(paramValues[0]);
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
        ArrayList<DistrictDTO> districts = (ArrayList<DistrictDTO>)dao.getDistrictDTOByYear(state, year); 
        return superDistrictService.validateSuperDistrict(districts);
        // return false;
    }

    @RequestMapping(value = "/createSD", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Integer> handleCreateSD(
            @RequestParam Map<String, String> requestParams,
            HttpServletRequest request, HttpServletResponse response) {

        return null;
    }
}
