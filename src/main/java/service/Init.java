package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.State;

@Service
public class Init {
	static final String CONFIG_FILE = "config.json";
	static final String STATES = "states";	// states object/node in json
	static final String NAME = "name";		// name object/node in json for state 
	static final String ID = "id";			// id object/node in json for state 
	static final String MEASURES = "measures"; // measures object/node in json
	static final String UI_COMPONENT = "ui_component"; // ui_component object/node in json
	static final String DB_CONNECTION = "db_connection"; // db_connection object/node in json
	private HashMap<Integer, State> states;
	private ArrayList<String> measures;
	private boolean superDistrictable;
	private int currentState;
	private int currentYear;
	private double strokeWidth;
	private int[] strokeColor;
	
	public Init() {
		states = new HashMap<Integer, State>();
		measures = new ArrayList<String>();

		// set default values
		currentState = -1;
		currentYear = -1;
		strokeWidth = 2;
		strokeColor = new int[] {255,255,255};
	}

	/**
	 * Read and parse configuration file
	 * Load state boundary data from database
	 */
	public void init() {
		
		// read and save initialization data 
		configure(CONFIG_FILE, this);
		
		//TODO: retrieve state boundary data from database
		
		// save boundary data to init object
		// for()
		// create state and save data to
		// states.put()
		
	}

	/**
	 * Read and parse data from configuration file
	 * Create and save state objects to initialization object
	 * @param configFile Configuration file
	 * @param init Initialization object 
	 */
	public void configure(String configFile, Init init) {
		//read and parse config file
		byte[] jsonData;
		try {
			// read configure file to string
			jsonData = Files.readAllBytes(Paths.get(configFile));

			ObjectMapper objectMapper = new ObjectMapper();

			//read root node of json
			JsonNode rootNode = objectMapper.readTree(jsonData);
			
			// read states tree
			JsonNode statesNode = rootNode.path(STATES);

			// read all states 
			System.out.println("Populating State dropdown...");
			Iterator<JsonNode> stateElements = statesNode.elements();
			while(stateElements.hasNext()) {
				JsonNode stateNode = stateElements.next();
				
				// create new state object
				State state = new State();

				// read state object data
				int id = stateNode.path(ID).asInt();
				String name = stateNode.path(NAME).asText();

				// store data to state
				state.setId(id);
				state.setName(name);
				
				// add state to init object
				init.getStates().put(id, state);
			}
			System.out.println("Done\n");

			// read all measures
			JsonNode measuresNode = rootNode.path(MEASURES);
			Iterator<JsonNode> measureElements = measuresNode.elements();

			System.out.println("Populating Measure dropdown...");
			while(measureElements.hasNext()){
				JsonNode measure = measureElements.next();
				//save measures somewhere
				init.measures.add(measure.asText());
			}
			System.out.println("Done\n");
			
			// TODO:read ui components
			
			// TODO:read db connection data

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Cannot open configuration file\nAborting...");
			System.exit(-1);
		}

	}
	
	public State getStateById(int stateId) {
		return states.get(stateId);
	}

	public Map<Integer, State> getStates() {
		return states;
	}

	public ArrayList<String> getMeasures() {
		return measures;
	}

	public void setMeasures(ArrayList<String> measures) {
		this.measures = measures;
	}
	
	public boolean isSuperDistrictable() {
		return superDistrictable;
	}
	
	public void setSuperDistrictable(boolean superDistrictable) {
		this.superDistrictable = superDistrictable;
	}

	public int getCurrentState() {
		return currentState;
	}

	public void setCurrentState(int currentState) {
		this.currentState = currentState;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
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

	public void setStates(HashMap<Integer, State> states) {
		this.states = states;
	}
	
}
