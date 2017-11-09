package persistence.dao;

import java.util.Collection;
import java.util.Map;

import model.Coordinate;
import model.District;
import model.ElectionData;
import model.GeoData;
import model.State;
import org.springframework.stereotype.Repository;

@Repository
public class DataAccessorImpl implements DataAccessor {

	public Collection<State> getStates() {
		// TODO Auto-generated method stub
		return null;
	}

	public State getState(int stateId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Integer, Collection<Coordinate>> getStateBoundaries() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<District> getDataByYear(int selectedState, int selectedYear) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<GeoData> getGeoDataByYear(int stateId, int year) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<ElectionData> getElectionDataByYear(int stateId, int year) {
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

}
