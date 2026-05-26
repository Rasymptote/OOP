package ru.nsu.babich.server.dsl

import ru.nsu.babich.dsl.contracts.Builder

class FieldConfigBuilder implements Builder<FieldConfig> {
    private int width = 10
    private int height = 10

    void width(int width) {
        this.width = width
    }

    void height(int height) {
        this.height = height
    }

    @Override
    FieldConfig build() {
        return new FieldConfig(width, height)
    }
}
