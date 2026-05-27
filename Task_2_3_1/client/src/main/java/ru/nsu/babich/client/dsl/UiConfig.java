package ru.nsu.babich.client.dsl;

public record UiConfig(
        WindowConfig window,
        BoardConfig board,
        ColorPalette palette
) {
}
