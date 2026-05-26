package ru.nsu.babich.server.dsl;

public record WebsocketConfig(
        String path,
        String appPrefix,
        String topicPrefix,
        RoutesConfig routes
) {
}
