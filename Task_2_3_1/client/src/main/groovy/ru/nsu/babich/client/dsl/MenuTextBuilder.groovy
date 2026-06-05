package ru.nsu.babich.client.dsl

import ru.nsu.babich.dsl.contracts.Builder

class MenuTextBuilder implements Builder<MenuTextConfig> {
    private String title
    private String foodHeader
    private String foodNormal
    private String foodBonus
    private String foodPoison
    private String startGame

    void title(String title) {
        this.title = title
    }

    void foodHeader(String foodHeader) {
        this.foodHeader = foodHeader
    }

    void foodNormal(String foodNormal) {
        this.foodNormal = foodNormal
    }

    void foodBonus(String foodBonus) {
        this.foodBonus = foodBonus
    }

    void foodPoison(String foodPoison) {
        this.foodPoison = foodPoison
    }

    void startGame(String startGame) {
        this.startGame = startGame
    }

    @Override
    MenuTextConfig build() {
        return new MenuTextConfig(
                title,
                foodHeader,
                foodNormal,
                foodBonus,
                foodPoison,
                startGame
        )
    }
}
