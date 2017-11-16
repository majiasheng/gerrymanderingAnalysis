package model;

public class District {

    private String stateShortName;
    private int districtNum;
    private GeoData geoData;
    private ElectionData electionData;
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

    public int getDistrictNum() {
        return districtNum;
    }

    public void setDistrictNum(int districtNum) {
        this.districtNum = districtNum;
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

    public void setElectionData(ElectionData electionData) {
        this.electionData = electionData;
    }

}
