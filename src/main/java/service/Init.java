package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	private static final String CONFIG_FILE = "/GerrymanderingAnalysis/src/main/resources/config.json";
	private static final String STATES = "states";	// states object/node in json
	private static final String NAME = "name";		// name object/node in json for state 
	private static final String ID = "id";			// id object/node in json for state 
	private static final String MEASURES = "measures"; // measures object/node in json
	private static final String UI_COMPONENT = "ui_component"; // ui_component object/node in json
	private static final String DB_CONNECTION = "db_connection"; // db_connection object/node in json
	private HashMap<Integer, State> states;
	private double strokeWidth;
	private int[] strokeColor;
	
	
	public Init() {
		states = new HashMap<Integer, State>();

		// set default values
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
		
		// load state boundary data from database 
		// getStateBoundaries()
		
		// save boundary data to map of state objects
		
		
	}
	
	/**
	 * Read and parse data from configuration file
	 * Save data to initialization object
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
			
			// read all measures
			JsonNode measuresNode = rootNode.path(MEASURES);
			Iterator<JsonNode> measureElements = measuresNode.elements();
			while(measureElements.hasNext()){
				JsonNode measure = measureElements.next();
				//TODO: save measures somewhere
				System.out.println("Measure: " + measure);
			}
			
			// TODO:read ui components
			
			// TODO:read db connection data
			
		} catch (IOException e) {
			System.out.println("Cannot open configuration file\nAborting...");
			System.exit(-1);
		}

		// save boundary data to init object
		// for()
		// create state and save data to
		// states.put()
		
	}
	
	public State getStateById(int stateId) {
		return states.get(stateId);
	}

	public Map<Integer, State> getStates() {
		return states;
	}
	
}
