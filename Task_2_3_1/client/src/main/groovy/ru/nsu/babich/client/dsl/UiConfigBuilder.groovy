package ru.nsu.babich.client.dsl

import ru.nsu.babich.dsl.contracts.Builder

class UiConfigBuilder implements Builder<UiConfig> {
    private WindowConfigBuilder windowConfigBuilder = new WindowConfigBuilder()
    private BoardConfigBuilder boardConfigBuilder = new BoardConfigBuilder()
    private ColorPaletteBuilder colorPaletteBuilder = new ColorPaletteBuilder()

    void window(Closure closure) {
        closure.delegate = windowConfigBuilder
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

    void board(Closure closure) {
        closure.delegate = boardConfigBuilder
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

    void palette(Closure closure) {
        closure.delegate = colorPaletteBuilder
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

    @Override
    UiConfig build() {
        return new UiConfig(
                windowConfigBuilder.build(),
                boardConfigBuilder.build(),
                colorPaletteBuilder.build()
        )
    }
}
