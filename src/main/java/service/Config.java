package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Config {

	private HashMap<Integer, String> states;
	private ArrayList<String> measures;
	private double strokeWidth;
	private int[] strokeColor;

	public Config() {
		// initialize and set default
		states = new HashMap<Integer, String>();
		measures = new ArrayList<String>();
		strokeWidth = 2;
		strokeColor = new int[] {255,255,255};
	}

	public HashMap<Integer, String> getStates() {
		return states;
	}

	public void setStates(HashMap<Integer, String> states) {
		this.states = states;
	}

	public ArrayList<String> getMeasures() {
		return measures;
	}

	public void setMeasures(ArrayList<String> measures) {
		this.measures = measures;
	}

	public double getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(double strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	public int[] getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(int[] strokeColor) {
		this.strokeColor = strokeColor;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("Config: \n");
		s.append("Stroke width: " + strokeWidth + "\n");
		s.append("Stroke color: " + Arrays.toString(strokeColor) + "\n");
		s.append("Measures: " + measures.toString() + "\n");
		s.append("State: " + states.toString() + "\n");
		return s.toString();
	}
}
