package model;

import java.util.ArrayList;

public class GeoData {
	
	private ArrayList<Coordinate> coordinates;	// district boundary coordinates
	private double area;
	
	public GeoData() {
		coordinates = new ArrayList<Coordinate>();
	}

	public ArrayList<Coordinate> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}
	
}
