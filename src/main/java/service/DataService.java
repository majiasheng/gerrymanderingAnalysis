package service;

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
	public Map<Integer, Collection<Coordinate>> getStateBoundaries();
	
	/**
	 * Loads data from database where stateid = currentState and year = currentYear
	 * @param selectedState
	 * @param selectedYear
	 * @return list of districts of year  pertaining to the state 
	 */
	public Collection<District> getDataByYear(int selectedState, int selectedYear);
	public Collection<GeoData> getGeoDataByYear(int stateId, int year);
	public Collection<ElectionData> getElectionDataByYear(int stateId, int year);
	public String getDistrictInfo(int districtId);
	public boolean upload(Object uploadTarget);
}
