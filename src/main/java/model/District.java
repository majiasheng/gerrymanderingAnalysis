package model;

public class District {
	private GeoData	geoData;
	private String	stateCode;
	private int		districtNum;
        private ElectionData electionData;
	
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
    public ElectionData getElectionData() {
        return electionData;
    }

    /**
     * @param electionData the electionData to set
     */
    public void setElectionData(ElectionData electionData) {
        this.electionData = electionData;
    }
}
