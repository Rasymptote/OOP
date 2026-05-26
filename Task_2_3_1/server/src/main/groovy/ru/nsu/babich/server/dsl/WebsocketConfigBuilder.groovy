package ru.nsu.babich.server.dsl

import ru.nsu.babich.dsl.contracts.Builder

class WebsocketConfigBuilder implements Builder<WebsocketConfig> {
    private String path = "/ws"
    private String appPrefix = "/app"
    private String topicPrefix = "/topic"
    private RoutesConfigBuilder routesConfigBuilder = new RoutesConfigBuilder()

    void path(String path) {
        this.path = path
    }

    void appPrefix(String appPrefix) {
        this.appPrefix = appPrefix
    }

    void topicPrefix(String topicPrefix) {
        this.topicPrefix = topicPrefix
    }

    void routes(Closure closure) {
        closure.delegate = routesConfigBuilder
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

    @Override
    WebsocketConfig build() {
        return new WebsocketConfig(
                path,
                appPrefix,
                topicPrefix,
                routesConfigBuilder.build()
        )
    }
}
