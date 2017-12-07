package persistence.dao;

import java.sql.Date;
import java.util.ArrayList;
import model.DistrictDTO;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Coordinate;
import model.District;
import model.ElectionData;
import model.GeoData;
import model.Party;
import model.State;
import model.Status;
import model.DemographicData;
import model.Snapshot;
import org.springframework.stereotype.Repository;
import persistence.JPAUtils;

@Repository
public class DataAccessorImpl implements DataAccessor {

    /**
     * Fetch a list of years in which the selected state has available (in
     * descending order).
     *
     * @param state short name of a state
     * @return a list of years in which the selected state has available, null
     * if no matching data found in database.
     */
    public Collection<Integer> getDataYearSetByState(String state) {
        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        // call mysql stored procedure
        String sql = "call GET_DATA_YEAR_SET_BY_STATE('" + state + "')";
        Query q = em.createNativeQuery(sql);
        List<Date> years_sql = q.getResultList();
        List<Integer> years = new ArrayList<Integer>();

        final int HEAD = 0;
        for (Date d : years_sql) {
            years.add(HEAD, d.toLocalDate().getYear());
        }

        em.getTransaction().commit();
        em.close();

        return years;
    }

    /**
     * Maps state data to a Data Transfer Object
     *
     * @param state short name of selected state
     * @param year selected year
     */
    private Collection<DistrictDTO> getDistrictDTOByYear(String state, int year) {

        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        // call mysql stored procedure
        String sql = "call GET_DISTRICTS_BY_STATE_AND_YEAR('" + state + "', " + year + ")";
        Query q = em.createNativeQuery(sql, "DistrictDTO");
        List<DistrictDTO> districtDTOes = q.getResultList();

        em.getTransaction().commit();
        em.close();

        // TODO: close connection pool when app exits?
        // JPAUtils.shutdown();
        return districtDTOes;
    }

    /**
     *
     * @param state selected state
     * @param year selected year
     * @return state model with data of the year selected
     */
    public State getStateByYear(String state, int year) {
        ArrayList<District> districts = (ArrayList<District>) getDistrictsDataByYear(state, year);
        return new State(year, state, districts);
    }

    /**
     *
     * @param state selected state
     * @param year selected year
     * @return a collection of district models
     */
    public Collection<District> getDistrictsDataByYear(String state, int year) {
        // retrieve district from database
        ArrayList<DistrictDTO> districtDTOes = (ArrayList<DistrictDTO>) getDistrictDTOByYear(state, year);
        ArrayList<District> districts = new ArrayList<District>();
        // convert dto to district model
        for (DistrictDTO dto : districtDTOes) {
            ElectionData electionData = new ElectionData(
                    dto.getDistrictNum(),
                    dto.getDemVotes(),
                    dto.getRepVotes(),
                    dto.getDemStatus(),
                    dto.getRepStatus(),
                    dto.getWinner()
            );
            GeoData geoData = new GeoData(
                    dto.getDistrictNum(),
                    dto.getBoundary()
            );
            DemographicData demogData = new DemographicData(
                    dto.getDistrictNum(),
                    dto.getPopulation(),
                    dto.getWhite(),
                    dto.getAfricanAmerican(),
                    dto.getAmericanNative(),
                    dto.getAsian(),
                    dto.getPacificIslander(),
                    dto.getOtherRace(),
                    dto.getTwoOrMoreRaces()
            );
            District district = new District(state, dto.getDistrictNum(), geoData, electionData, demogData);
            // add district to list
            districts.add(district);
        }
        return districts;
    }

    public boolean takeSnapShot(Snapshot snapshot) {
        System.out.println("\n\nTaking snapshot...");
        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        boolean success = false;

        try {
            em.persist(snapshot);
            em.getTransaction().commit();
            em.close();
            success = true;
        } catch (Exception e) {
            //TODO: check specific exception
            e.printStackTrace();
            System.err.println("Error in adding snapshot to database");
        }
        return success;
    }
    
    public Map<Integer, Collection<Coordinate>> getStateBoundaries() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Collection<District> getDataByYear(int selectedState, int selectedYear) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Collection<GeoData> getGeoDataByYear(int stateId, int year) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Collection<ElectionData> getElectionDataByYear(int stateId, int year) {
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

    public Collection<State> getStates() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
