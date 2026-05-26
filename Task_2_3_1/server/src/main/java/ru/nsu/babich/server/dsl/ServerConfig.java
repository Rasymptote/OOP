package ru.nsu.babich.server.dsl;

import ru.nsu.babich.dsl.contracts.Configurable;

public record ServerConfig(
        NetworkConfig network,
        GameConfig game
) implements Configurable {
}
