package service.data;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Service;

import model.District;
import model.ElectionData;
import model.GeoData;
import model.State;

@Service
public class DataServiceImpl implements DataService {

    // @Autowired
    // private DataAccessor dao;
    public Collection<State> getStates() {
        // TODO Auto-generated method stub
        return null;
    }

    public State getState(String stateCode) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see service.DataService#getDataYearSet(String)
     */
    public Collection<Integer> getDataYearSetByCode(String code) {
        // TEST DATA
        ArrayList<Integer> dataYearSet = new ArrayList<Integer>();
        dataYearSet.add(1990);
        dataYearSet.add(1991);
        return dataYearSet;
        // END TEST DATA
        // return null;
    }

    /*
     * (non-Javadoc)
     * @see service.DataService#getDataByYear(String, int)
     */
    public Collection<District> getDataByYear(String selectedState, int selectedYear) {
        // return dao.getDataByYear(selectedState, selectedYear);
        return null;
    }

    public Collection<ElectionData> getElectionDataByYear(String stateCode, int year) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getDistrictInfo(int districtId) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean upload(Object uploadTarget) {
        // TODO Auto-generated method stub
        return false;
    }

    public Collection<GeoData> getGeoDataByYear(String stateCode, int year) {
        // TODO Auto-generated method stub
        return null;
    }

}
