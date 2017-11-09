package model;

import java.util.ArrayList;
import java.util.Collection;

public class GeoData {

    // private Collection<Coordinate> coordinates;	// district boundary coordinates
    private String boundary;    // district boundary as geojson in text format
    private double area;

    public GeoData() {
        // coordinates = new ArrayList<Coordinate>();
    }

    // public Collection<Coordinate> getCoordinates() {
    //    return coordinates;
    // }
    // public void setCoordinates(ArrayList<Coordinate> coordinates) {
    //    this.coordinates = coordinates;
    // }
    public String getBoundary() {
        return boundary;
    }

    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

}
