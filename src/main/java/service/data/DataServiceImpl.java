package service.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Service;

import model.District;
import model.ElectionData;
import model.GeoData;
import model.State;
import org.springframework.beans.factory.annotation.Autowired;
import persistence.dao.DataAccessor;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataAccessor dao;

    /*
     * (non-Javadoc)
     * @see service.DataService#getDataYearSet(String)
     */
    public Collection<Integer> getDataYearSetByState(String state) {
         return dao.getDataYearSetByState(state);
    }

    /**
     *
     * @param state selected state
     * @param year selected year
     * @return state model with data of the year selected
     */
    public State getStateByYear(String state, int year) {
        return dao.getStateByYear(state, year);
    }

    /*
     * (non-Javadoc)
     * @see service.DataService#getDistrictsDataByYear(String, int)
     */
    public Collection<District> getDistrictsDataByYear(String selectedState, int selectedYear) {
        return dao.getDistrictsDataByYear(selectedState, selectedYear);
    }

    /**
     * Convert list of districts into json
     *
     * @param districts list of districts
     * @return json string
     */
    public String districtGeoDataToJson(Collection<District> districts) {
        // Turn list to string
        String geojsonStr = "{\"type\":\"FeatureCollection\",\"features\":[";
        String geojsonStrEnd = "]}";
        // guard against empty list
        boolean ran = false;
        for (District district : districts) {
            ran = true;
            ObjectMapper mapper = new ObjectMapper();
            String electionDataJson = null;
            try {
                electionDataJson = mapper.writeValueAsString(district.getElectionData());
            } catch (JsonProcessingException ex) {
                System.err.println(ex);
            }
            String geojson = district.getGeoData().getBoundary();
            // geojson = 

            geojsonStr += geojson + ",";
        }
        // remove the extra comma
        if (ran) {
            geojsonStr = geojsonStr.substring(0, geojsonStr.length() - 1);
        }
        return geojsonStr + geojsonStrEnd;
    }

    public Collection<ElectionData> getElectionDataByYear(String state, int year) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getDistrictInfo(int districtId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean upload(Object uploadTarget) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Collection<GeoData> getGeoDataByYear(String state, int year) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Collection<State> getStates() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
