package model;

import java.util.ArrayList;
import java.util.HashMap;

public class State {
	private int id;
	private String name;
	private Coordinate focusLatLong;
	private HashMap<Integer, ArrayList<District>> districts;
	private ArrayList<Coordinate> boundary;
	
	public State() {
		districts = new HashMap<Integer, ArrayList<District>>();
		boundary = new ArrayList<Coordinate>();
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Coordinate getFocusLatLong() {
		return focusLatLong;
	}
	public void setFocusLatLong(Coordinate focusLatLong) {
		this.focusLatLong = focusLatLong;
	}
	public HashMap<Integer, ArrayList<District>> getDistricts() {
		return districts;
	}
	public void setDistricts(HashMap<Integer, ArrayList<District>> districts) {
		this.districts = districts;
	}
	public ArrayList<Coordinate> getBoundary() {
		return boundary;
	}
	public void setBoundary(ArrayList<Coordinate> boundary) {
		this.boundary = boundary;
	}
	
}
