package ru.nsu.babich.server;

import java.io.IOException;
import java.nio.file.Path;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.nsu.babich.dsl.ConfigLoader;
import ru.nsu.babich.server.dsl.ServerConfigBuilder;

/**
 * Entry point for the Snake game server application.
 */
@SpringBootApplication
public class ServerApplication {
    /**
     * Starts Spring Boot server application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) throws IOException {
        var builder = new ServerConfigBuilder();
        var loader = new ConfigLoader<>(builder);
        var path = Path.of("server.config.groovy");
//        var config = loader.load(path);

        SpringApplication.run(ServerApplication.class, args);
    }
}
