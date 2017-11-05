package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Coordinate;
import model.District;
import model.ElectionData;
import model.GeoData;
import model.State;
import persistence.dao.DataAccessor;

@Service
public class DataServiceImpl implements DataService {
	
	// @Autowired
	// private DataAccessor dao;

	public Collection<State> getStates() {
		// TODO Auto-generated method stub
		return null;
	}

	public State getState(int stateId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see service.DataService#getDataYearSet(int)
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
	 * @see service.DataService#getDataByYear(int, int)
	 */
	public Collection<District> getDataByYear(int selectedState, int selectedYear) {
		// return dao.getDataByYear(selectedState, selectedYear);
		return null;
	}

	public Collection<GeoData> getGeoDataByYear(String stateId, int year) {
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

	public Collection<District> getDataByYear(String selectedState, int selectedYear) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<GeoData> getGeoDataByYear(int stateId, int year) {
		// TODO Auto-generated method stub
		return null;
	}


}
