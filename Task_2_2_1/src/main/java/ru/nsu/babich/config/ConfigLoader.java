package ru.nsu.babich.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import ru.nsu.babich.dto.PizzeriaSimulatorDto;

/**
 * A class responsible for loading the configuration of the pizzeria simulator from a JSON file.
 */
public class ConfigLoader {

    private final String configFilePath;

    /**
     * Constructs a ConfigLoader with the specified path to the configuration file.
     *
     * @param configFilePath Path to the JSON configuration file.
     */
    public ConfigLoader(String configFilePath) {
        this.configFilePath = configFilePath;
    }

    /**
     * Loads the configuration from the JSON file and returns it as a PizzeriaSimulatorDto object.
     *
     * @return A PizzeriaSimulatorDto object containing the configuration of the pizzeria simulator.
     * @throws IOException If there is an error reading the configuration file.
     */
    public PizzeriaSimulatorDto load() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(configFilePath), PizzeriaSimulatorDto.class);
    }
}
