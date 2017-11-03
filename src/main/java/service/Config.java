package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 
 * @author Jia Sheng Ma (jiasheng.ma@yahoo.com)
 *
 */
public class Config {

	private HashMap<String, String> states;
	private ArrayList<String> measures;
	private double strokeWidth;
	private int[] strokeColor;
	private int defaultYear;

	public Config() {
		// initialize and set default
		measures = new ArrayList<String>();
		strokeWidth = 2;
		strokeColor = new int[] {255,255,255};
	}

	public HashMap<String, String> getStates() {
		return states;
	}

	public void setStates(HashMap<String, String> states) {
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
	
	public int getDefaultYear() {
		return defaultYear;
	}

	public void setDefaultYear(int defaultYear) {
		this.defaultYear = defaultYear;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("Config: \n");
		s.append("Stroke width: " + strokeWidth + "\n");
		s.append("Stroke color: " + Arrays.toString(strokeColor) + "\n");
		s.append("Measures: " + measures.toString() + "\n");
		s.append("State: " + states.toString() + "\n");
                s.append("Default Year: " + defaultYear + "\n");
		return s.toString();
	}
}
