package persistence;

import java.util.Collection;
import java.util.Map;

import model.Coordinate;
import model.District;
import model.ElectionData;
import model.GeoData;
import model.State;

public interface DataAccessor {
	public Collection<State> getStates();
	public State getState(int stateId);
	public Map<Integer, Collection<Coordinate>> getStateBoundaries();
	public Collection<District> loadDataByYear(int currentState, int currentYear);
	public Collection<GeoData> getGeoDataByYear(int stateId, int year);
	public Collection<ElectionData> getElectionDataByYear(int stateId, int year);
	public String getDistrictInfo(int districtId);
	public boolean upload(Object uploadTarget);
}
