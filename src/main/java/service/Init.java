package service;

import model.Config;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Init {

    public static final String CONFIG_FILE = "config.json"; // config file name
    public static final int ERROR = -1;
    private Config config;

    public Init() {
        // set default values

    }

    /**
     * Do initialization: read, parse configuration file
     */
    public void init() {
        System.out.println("\n>> Initializing...\n");
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
            System.exit(ERROR);
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
