package service.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

import model.District;
import model.ElectionData;
import model.GeoData;
import model.Party;
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

    //TODO:
    public boolean doExport(String state, int year) {
        ArrayList<District> districts = (ArrayList<District>) dao.getDistrictsDataByYear(state, year);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("election.csv"));
            StringBuilder csv = new StringBuilder();

            // out put header
            // state name, congress, district number, party name, votes, election status, iswinner 
            csv.append("District Number").append(",")
                    .append("State").append(",")
                    .append("Congress").append(",")
                    .append("Republican Vote").append(",")
                    .append("Republican Status").append(",")
                    .append("Democratic Vote").append(",")
                    .append("Democratic Status").append(",")
                    .append("Winner")
                    .append("\n");
            // out put rows
            for (District d : districts) {
                String winner = "N/A";
                if ((d.getElectionData().getWinner()).equals(Party.DEMOCRATIC)) {
                    winner = "D";
                } else if ((d.getElectionData().getWinner()).equals(Party.REPUBLICAN)) {
                    winner = "R";
                }
                
                String repStatus = d.getElectionData().getRepStatus().name();
                String demStatus = d.getElectionData().getDemStatus().name();

                csv.append(d.getDistrictNum()).append(",")
                        .append(d.getStateShortName()).append(",")
                        //TODO: calculate congress.
                        .append("Congress").append(",")
                        .append(d.getElectionData().getRepVotes()).append(",")
                        .append((repStatus == null)?"N/A":repStatus).append(",")
                        .append(d.getElectionData().getDemVotes()).append(",")
                        .append((demStatus == null)?"N/A":demStatus).append(",")
                        .append(winner)
                        .append("\n");
            }
            pw.write(csv.toString());
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            // Logger.getLogger(DataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }

        return false;
    }

}
