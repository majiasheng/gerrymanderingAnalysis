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
	// private boolean superDistrictable;
	// private String selectedState;
	private int selectedYear; // 
	private Config config;

	public Init() {
		// set default values
		// selectedState = null;
		selectedYear = -1;
	}

	/**
	 * Read and parse configuration file
	 * Load state boundary data from database
	 */
	public void init() {
		// read and save initialization data 
		configure(CONFIG_FILE, this);
		// set default selected year
		selectedYear = config.getDefaultYear();
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
			// read configure file
			jsonData = Files.readAllBytes(Paths.get(configFile));
			ObjectMapper objectMapper = new ObjectMapper();
			// parse json and save config object
			init.setConfig(objectMapper.readValue(jsonData, Config.class));
			System.out.println(init.getConfig());
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Cannot open configuration file\nAborting...");
			System.exit(-1);
		}
	}
	
	// public boolean isSuperDistrictable() {
	// 	return superDistrictable;
	// }
	
	// public void setSuperDistrictable(boolean superDistrictable) {
	// 	this.superDistrictable = superDistrictable;
	// }
	
	// public String getSelectedState() {
	// 	return selectedState;
	// }

	// public void setSelectedState(String selectedState) {
	// 	this.selectedState = selectedState;
	// }

	public int getSelectedYear() {
		return selectedYear;
	}

	public void setSelectedYear(int selectedYear) {
		this.selectedYear = selectedYear;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
	public Config getConfig() {
		return config;
	}

}
