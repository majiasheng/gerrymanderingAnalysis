package service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import model.State;

@Service
public class Init {
	public static final String CONFIG = "resources/config.json";
	private Map<Integer, State> stateData;
	private double strokeWidth;
	private int[] strokeColor;
	
	
	public Init() {
		stateData = new HashMap<Integer, State>();

		// set default values
		strokeWidth = 2;
		strokeColor = new int[] {255,255,255};
		
	}
	
	public void loadUIComponent() {
		//TODO: read and parse config file
	}
	
}
