package ru.nsu.babich.client.dsl

import ru.nsu.babich.dsl.contracts.Builder

class WindowConfigBuilder implements Builder<WindowConfig> {
    private int width = 600
    private int height = 600

    void width(int width) {
        this.width = width
    }

    void height(int height) {
        this.height = height
    }

    @Override
    WindowConfig build() {
        return new WindowConfig(width, height)
    }
}
