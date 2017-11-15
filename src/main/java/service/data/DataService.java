package service.data;

import java.util.Collection;
import model.District;
import model.ElectionData;
import model.GeoData;
import model.State;

public interface DataService {

    public Collection<State> getStates();

    public State getState(String stateCode);

    /**
     * Fetch a list of years in which the selected state has available (in
     * descending order).
     *
     * @param code
     * @return a list of years in which the selected state has available, null
     * if no matching data found in database.
     */
    public Collection<Integer> getDataYearSetByCode(String code);

    /**
     * Loads district data from database.
     *
     * @param selectedState 
     * @param selectedYear
     * @return a list of districts in the selected year of the selected state,
     * null if no matching data found in database.
     */
    public Collection<District> getDataByYear(String selectedState, int selectedYear);
    
    /**
     * Converts district geo data to json string
     *
     * @param districts
     * @return all districts in the selected year of the selected state,
     * null if no matching data found in database.
     */
    public String districtGeoDataToJson(Collection<District> districts);

    public Collection<GeoData> getGeoDataByYear(String stateCode, int year);

    public Collection<ElectionData> getElectionDataByYear(String stateCode, int year);

    public String getDistrictInfo(int districtId);

    public boolean upload(Object uploadTarget);
}
