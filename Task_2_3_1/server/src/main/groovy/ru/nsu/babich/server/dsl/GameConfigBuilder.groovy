package ru.nsu.babich.server.dsl

import ru.nsu.babich.dsl.contracts.Builder

class GameConfigBuilder implements Builder<GameConfig> {
    private FieldConfigBuilder fieldConfigBuilder = new FieldConfigBuilder()
    private int tickDuration = 300
    private int playerBufferSize = 5
    private int foodReplenishCount = 3
    private int randomSeed = 12345

    void field(Closure closure) {
        closure.delegate = fieldConfigBuilder
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

    void tickDuration(int tickDuration) {
        this.tickDuration = tickDuration
    }

    void playerBufferSize(int playerBufferSize) {
        this.playerBufferSize = playerBufferSize
    }

    void foodReplenishCount(int foodReplenishCount) {
        this.foodReplenishCount = foodReplenishCount
    }

    void randomSeed(int randomSeed) {
        this.randomSeed = randomSeed
    }

    @Override
    GameConfig build() {
        return new GameConfig(
                fieldConfigBuilder.build(),
                tickDuration,
                playerBufferSize,
                foodReplenishCount,
                randomSeed
        )
    }
}
