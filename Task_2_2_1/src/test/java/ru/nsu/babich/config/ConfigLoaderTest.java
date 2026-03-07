package ru.nsu.babich.config;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Files;
import java.nio.file.Path;
import ru.nsu.babich.dto.PizzeriaSimulatorDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigLoaderTest {

    @TempDir
    Path tempDir;

    @Test
    void checkLoadValidConfig() throws IOException {
        Path configFile = tempDir.resolve("valid.json");
        String VALID_CONFIG = """
                {
                     "workTimeMs": 50000,
                     "bakers": [{"id": 1, "cookingSpeedMs": 2}],
                     "couriers": [],
                     "storageCapacity": 15
                }
                """;
        Files.writeString(configFile, VALID_CONFIG);

        ConfigLoader loader = new ConfigLoader(configFile.toString());
        PizzeriaSimulatorDto dto = loader.load();

        assertEquals(50000, dto.workTime());
        assertEquals(15, dto.storageCapacity());
        assertEquals(1, dto.bakers().size());
        assertEquals(0, dto.couriers().size());
    }

    @Test
    void checkLoadMissingFile() {
        ConfigLoader loader = new ConfigLoader(tempDir.resolve("missing.json").toString());
        assertThrows(IOException.class, loader::load);
    }

    @Test
    void checkLoadInvalidConfig() throws IOException {
        Path configFile = tempDir.resolve("indalid.json");
        String INVALID_CONFIG = """
                           {
                                "workTimeMs": 0,
                           }
                """;
        Files.writeString(configFile, INVALID_CONFIG);

        ConfigLoader loader = new ConfigLoader(configFile.toString());
        assertThrows(IOException.class, loader::load);
    }
}