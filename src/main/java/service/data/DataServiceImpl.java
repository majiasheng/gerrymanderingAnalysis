package service.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;
import org.springframework.stereotype.Service;

import model.District;
import model.ElectionData;
import model.GeoData;
import model.Snapshot;
import model.State;
import org.json.JSONObject;
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
            String demographicDataJson = null;
            try {
                if (district.getElectionData() != null) {
                    electionDataJson = mapper.writeValueAsString(district.getElectionData());
                }
                if (district.getDemographicData() != null) {
                    demographicDataJson = mapper.writeValueAsString(district.getDemographicData());
                }
            } catch (JsonProcessingException ex) {
                System.err.println(ex);
            }
            String distJson = district.getGeoData().getBoundary();
            if (electionDataJson != null || demographicDataJson != null) {
                JSONObject distJsonObj = new JSONObject(distJson);
                JSONObject childobject = distJsonObj.getJSONObject("properties");
                if (electionDataJson != null) {
                    childobject.put("electionData", new JSONObject(electionDataJson));
                }
                if (demographicDataJson != null) {
                    childobject.put("demographicData", new JSONObject(demographicDataJson));
                }
                distJson = distJsonObj.toString();
            }
            geojsonStr += distJson + ",";
        }
        // remove the extra comma
        if (ran) {
            geojsonStr = geojsonStr.substring(0, geojsonStr.length() - 1);
        }
        return geojsonStr + geojsonStrEnd;
    }
    
    
    public boolean takeSnapShot(Snapshot snapshot) {
        return dao.takeSnapShot(snapshot);
    }

    public Collection<ElectionData> getElectionDataByYear(String state, int year) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getDistrictInfo(int districtId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean upload(Object uploadTarget) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<GeoData> getGeoDataByYear(String state, int year) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<State> getStates() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
