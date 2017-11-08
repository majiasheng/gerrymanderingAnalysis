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
    private Config config;

    public Init() {
        // set default values
        
    }

    /**
     * Do initialization: read, parse configuration file 
     */
    public void init() {
        
        config = getConfiguration(CONFIG_FILE);
        System.out.println(this.getConfig());
        
    }

    /**
     * Read, parse data from configuration file
     *
     * @param configFile Configuration file
     * @return configuration object
     */
    public Config getConfiguration(String configFile) {
        //read and parse config file
        byte[] jsonData;
        try {
            // read getConfiguration file
            jsonData = Files.readAllBytes(Paths.get(configFile));
            ObjectMapper objectMapper = new ObjectMapper();

            // read and return configuration
            return objectMapper.readValue(jsonData, Config.class);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Cannot open configuration file\nAborting...");
            System.exit(-1);
        }
        return null;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public Config getConfig() {
        return config;
    }

}
