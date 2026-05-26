package ru.nsu.babich.client.dsl

import ru.nsu.babich.dsl.contracts.Builder

class BoardConfigBuilder implements Builder<BoardConfig> {
    private int width = 600
    private int height = 600
    private String primaryColor = "#1f2937"
    private String secondaryColor = "#111827"

    void width(int width) {
        this.width = width
    }

    void height(int height) {
        this.height = height
    }

    void primaryColor(String color) {
        this.primaryColor = color
    }

    void secondaryColor(String color) {
        this.secondaryColor = color
    }

    @Override
    BoardConfig build() {
        return new BoardConfig(width, height, primaryColor, secondaryColor)
    }
}
