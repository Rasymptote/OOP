package ru.nsu.babich.server.dsl

import ru.nsu.babich.dsl.contracts.DslBuilder

class ServerConfigBuilder implements DslBuilder<ServerConfig> {
    private NetworkConfigBuilder networkConfigBuilder = new NetworkConfigBuilder()
    private GameConfigBuilder gameConfigBuilder = new GameConfigBuilder()

    void network(Closure closure) {
        closure.delegate = networkConfigBuilder
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

    void game(Closure closure) {
        closure.delegate = gameConfigBuilder
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

    @Override
    void bind(Binding binding) {
        binding.setVariable("network", this.&network)
        binding.setVariable("game", this.&game)
    }

    @Override
    ServerConfig build() {
        return new ServerConfig(
                networkConfigBuilder.build(),
                gameConfigBuilder.build()
        )
    }
}
