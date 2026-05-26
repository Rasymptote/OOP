package ru.nsu.babich.client.dsl

import ru.nsu.babich.dsl.contracts.Builder

class ColorPaletteBuilder implements Builder<ColorPalette>{
    private String snake = "#22c55e"
    private String foodNormal = "#ff5c7a"
    private String foodBonus = "#ffd166"
    private String foodPoison = "#a855f7"

    void snake(String color) {
        this.snake = color
    }

    void foodNormal(String color) {
        this.foodNormal = color
    }

    void foodBonus(String color) {
        this.foodBonus = color
    }

    void foodPoison(String color) {
        this.foodPoison = color
    }

    @Override
    ColorPalette build() {
        return new ColorPalette(snake, foodNormal, foodBonus, foodPoison)
    }
}
