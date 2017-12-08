package persistence.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import model.Coordinate;
import model.District;
import model.DistrictDTO;
import model.ElectionData;
import model.GeoData;
import model.Snapshot;
import model.State;

public interface DataAccessor {

    /**
     * Fetch a list of years in which the selected state has available (in
     * descending order).
     *
     * @param state short name of a state
     * @return a list of years in which the selected state has available, null
     * if no matching data found in database.
     */
    public Collection<Integer> getDataYearSetByState(String state);

    /**
     *
     * @param state selected state
     * @param year selected year
     * @return state model with data of the year selected
     */
    public State getStateByYear(String state, int year);

    /**
     *
     * @param state selected state
     * @param year selected year
     * @return a collection of district models
     */
    public Collection<District> getDistrictsDataByYear(String state, int year);
    
    public Collection<DistrictDTO> getDistrictDTOByYear(String state, int year);

    public Map<Integer, Collection<Coordinate>> getStateBoundaries();

    public Collection<GeoData> getGeoDataByYear(int stateId, int year);

    public Collection<ElectionData> getElectionDataByYear(int stateId, int year);

    public String getDistrictInfo(int districtId);

    public boolean upload(Object uploadTarget);

    public Collection<State> getStates();
    
    public boolean takeSnapShot(Snapshot snapshot);

    public List<Snapshot> getSnapshotsByUserId(int id);
}
