package ru.nsu.babich.server.dsl

import ru.nsu.babich.dsl.contracts.Builder

class NetworkConfigBuilder implements Builder<NetworkConfig> {
    private String host = "localhost"
    private int port = 8080
    private WebsocketConfigBuilder websocketConfigBuilder = new WebsocketConfigBuilder()

    void host(String host) {
        this.host = host
    }

    void port(int port) {
        this.port = port
    }

    void websocket(Closure closure) {
        closure.delegate = websocketConfigBuilder
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

    @Override
    NetworkConfig build() {
        return new NetworkConfig(
                host,
                port,
                websocketConfigBuilder.build()
        )
    }
}
