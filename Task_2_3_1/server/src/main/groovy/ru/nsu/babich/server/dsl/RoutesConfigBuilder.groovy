package ru.nsu.babich.server.dsl

import ru.nsu.babich.dsl.contracts.Builder

class RoutesConfigBuilder implements Builder<RoutesConfig> {
    private String state = "state"
    private String join = "join"
    private String direction = "direction"

    void state(String state) {
        this.state = state
    }

    void join(String join) {
        this.join = join
    }

    void direction(String direction) {
        this.direction = direction
    }

    @Override
    RoutesConfig build() {
        return new RoutesConfig(state, join, direction)
    }
}
