package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	private boolean superDistrictable;
	private int currentState;
	private int currentYear;
	private Config config;
	
	private String hostname;
	private String dbname;
	private String username;
	private String password;
	
	public Init() {
		
		// set default values
		currentState = -1;
		currentYear = -1;
		
	}

	/**
	 * Read and parse configuration file
	 * Load state boundary data from database
	 */
	public void init() {
		
		// read and save initialization data 
		configure(CONFIG_FILE, this);
		
		/*TODO: retrieve state boundary data from database, 
		may not need it (leaflet js may have it)*/
		
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
			// read config object
			init.setConfig(objectMapper.readValue(jsonData, Config.class));
			System.out.println(init.getConfig());
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Cannot open configuration file\nAborting...");
			System.exit(-1);
		}
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
	
	public void setConfig(Config config) {
		this.config = config;
	}
	public Config getConfig() {
		return config;
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
