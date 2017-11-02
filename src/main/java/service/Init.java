package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Init {
	// config file name
	static final String CONFIG_FILE = "config.json";
	private boolean superDistrictable;
	private int currentState;
	private int currentYear;
	private Config config;

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

}
