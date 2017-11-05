package service.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import model.Coordinate;
import model.District;
import model.ElectionData;
import model.GeoData;
import model.State;

public interface DataService {
	public Collection<State> getStates();
	public State getState(int stateId);
	
	/**
	 * Fetch a list of years in which the selected state has available (in descending order).
	 * @param code
	 * @return a list of years in which the selected state has available 
	 */
	public Collection<Integer> getDataYearSetByCode(String code);

	/**
	 * Loads district data from database where stateid = selectedState and year = selectedYear
	 * @param selectedState
	 * @param selectedYear
	 * @return a list of districts in the selected year of the selected state, null if no data in database  
	 */
	public Collection<District> getDataByYear(String selectedState, int selectedYear);
	public Collection<GeoData> getGeoDataByYear(int stateId, int year);
	public Collection<ElectionData> getElectionDataByYear(int stateId, int year);
	public String getDistrictInfo(int districtId);
	public boolean upload(Object uploadTarget);
}
