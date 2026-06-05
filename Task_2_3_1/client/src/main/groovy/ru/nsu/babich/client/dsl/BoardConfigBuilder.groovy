package ru.nsu.babich.client.dsl

import ru.nsu.babich.dsl.contracts.Builder

class BoardConfigBuilder implements Builder<BoardConfig> {
    private String primaryColor = "#1f2937"
    private String secondaryColor = "#111827"

    void primaryColor(String color) {
        this.primaryColor = color
    }

    void secondaryColor(String color) {
        this.secondaryColor = color
    }

    @Override
    BoardConfig build() {
        return new BoardConfig(primaryColor, secondaryColor)
    }
}
