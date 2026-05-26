package ru.nsu.babich.server.dsl;

public record NetworkConfig(
        String host,
        int port,
        WebsocketConfig websocket
) {
}
