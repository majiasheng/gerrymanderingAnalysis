package service.file;

/**
 *
 * @author tianyilan
 */
public class Geo {

    private String stateName;
    private int districtNum;
    private int congress;
    private String geoText;
    
    public Geo() {
    }

    public Geo(String stateName, int districtNum, String geoText, int congress) {
        this.stateName = stateName;
        this.districtNum = districtNum;
        this.geoText = geoText;
        this.congress = congress;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getDistrictNum() {
        return districtNum;
    }

    public void setDistrictNum(int districtNum) {
        this.districtNum = districtNum;
    }

    public String getGeoText() {
        return geoText;
    }

    public void setGeoText(String geoText) {
        this.geoText = geoText;
    }

    public int getCongress() {
        return congress;
    }

    public void setCongress(int congress) {
        this.congress = congress;
    }
    
}
