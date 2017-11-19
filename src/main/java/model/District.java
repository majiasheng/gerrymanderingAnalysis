package model;

public class District {

    private GeoData geoData;
    private String stateCode;
    private int districtNum;
    private ElectionData electionData;
    private DemographicData demographicData;

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public int getDistrictNum() {
        return districtNum;
    }

    public void setDistrictNum(int districtNum) {
        this.districtNum = districtNum;
    }

    /**
     * @return the electionData
     */
    private String stateShortName;
    //TODO: add year ?

    public District() {
    }

    public District(String stateShortName, int districtNum, GeoData geoData, ElectionData electionData) {
        this.stateShortName = stateShortName;
        this.districtNum = districtNum;
        this.geoData = geoData;
        this.electionData = electionData;
    }

    public String getStateShortName() {
        return stateShortName;
    }

    public void setStateShortName(String stateShortName) {
        this.stateShortName = stateShortName;
    }

    public GeoData getGeoData() {
        return geoData;
    }

    public void setGeoData(GeoData geoData) {
        this.geoData = geoData;
    }

    public ElectionData getElectionData() {
        return electionData;
    }

    /**
     * @param electionData the electionData to set
     */
    public void setElectionData(ElectionData electionData) {
        this.electionData = electionData;
    }

    public DemographicData getDemographicData() {
        return demographicData;
    }

    public void setDemographicData(DemographicData demographicData) {
        this.demographicData = demographicData;
    }

}
