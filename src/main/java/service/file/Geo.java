package service.file;

/**
 *
 * @author tianyilan
 */
public class Geo {

    private String stateName;
    private int districtNum;
    private String geoText;

    public Geo() {
    }

    public Geo(String stateName, int districtNum, String geoText) {
        this.stateName = stateName;
        this.districtNum = districtNum;
        this.geoText = geoText;
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

}
