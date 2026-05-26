package ru.nsu.babich.server.dsl;

public record GameConfig(
        FieldConfig field,
        int tickDuration,
        int playerBufferSize,
        int foodReplenishCount,
        int randomSeed
) {
}
