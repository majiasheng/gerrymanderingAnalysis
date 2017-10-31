package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.State;
import persistence.DBConnection;

@Service
public class Init {
	static final String CONFIG_FILE = "config.json";
	static final String STATES = "states";	// states object/node in json
	static final String NAME = "name";		// name object/node in json for state 
	static final String ID = "id";			// id object/node in json for state 
	static final String MEASURES = "measures"; // measures object/node in json
	static final String UI_COMPONENT = "ui_component"; // ui_component object/node in json
	static final String STROKE_WIDTH = "stroke_width";
	static final String STROKE_COLOR = "stroke_color";
	static final String DB_CONNECTION = "db_connection"; // db_connection object/node in json
	static final String HOSTNAME = "hostname";
	static final String DBNAME = "dbname";
	static final String USERNAME = "username";
	static final String PASSWORD = "password";
	private HashMap<Integer, State> states;
	private ArrayList<String> measures;
	private boolean superDistrictable;
	private int currentState;
	private int currentYear;
	private double strokeWidth;
	private int[] strokeColor;
	private String hostname;
	private String dbname;
	private String username;
	private String password;
	
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
		
		//TODO: retrieve state boundary data from database, may not need it (leaflet js may have it)
		
		// save boundary data to init object
		// for()
		// create state and save data to
		// states.put()
		
	}

	/**
	 * Read, parse data from configuration file
	 * Save data to init object
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
			

			// read all states 
			System.out.println("Populating State dropdown...");
			JsonNode statesNode = rootNode.path(STATES);
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
			System.out.println("Populating Measure dropdown...");
			JsonNode measuresNode = rootNode.path(MEASURES);
			Iterator<JsonNode> measureElements = measuresNode.elements();
			while(measureElements.hasNext()){
				JsonNode measure = measureElements.next();
				//save measures somewhere
				init.measures.add(measure.asText());
			}
			System.out.println("Done\n");
			
			// read ui components
			JsonNode uiComponentNode = rootNode.path(UI_COMPONENT);
			JsonNode strokeWidthNode = uiComponentNode.path(STROKE_WIDTH);
			init.setStrokeWidth(strokeWidthNode.asDouble());
			//TODO:
//			JsonNode strokeColorNode = uiComponentNode.path(STROKE_COLOR);
//			Iterator<JsonNode> strokeColorElements = strokeColorNode.elements();
//			while(strokeColorElements.hasNext()) {
//				JsonNode color = strokeColorElements.next();
//				//TODO: store/use this
//				color.asDouble();
//			}
			
			// read db connection data
			JsonNode dbConnNode = rootNode.path(DB_CONNECTION);
			init.setHostname(dbConnNode.path(HOSTNAME).asText());
			init.setDbname(dbConnNode.path(DBNAME).asText());
			init.setUsername(dbConnNode.path(USERNAME).asText());
			init.setPassword(dbConnNode.path(PASSWORD).asText());
			
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

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
