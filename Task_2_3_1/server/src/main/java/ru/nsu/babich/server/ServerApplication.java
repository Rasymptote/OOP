package ru.nsu.babich.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
