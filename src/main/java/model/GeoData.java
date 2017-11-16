package model;

public class GeoData {

    private int districtNum;
    private String boundary;    // district boundary as geojson in text format

    public GeoData() {
    }

    public GeoData(int districtNum, String boundary) {
        this.districtNum = districtNum;
        this.boundary = boundary;
    }

    public int getDistrictNum() {
        return districtNum;
    }

    public void setDistrictNum(int districtNum) {
        this.districtNum = districtNum;
    }

    public String getBoundary() {
        return boundary;
    }

    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

}
