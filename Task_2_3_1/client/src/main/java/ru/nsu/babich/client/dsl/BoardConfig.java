package ru.nsu.babich.client.dsl;

public record BoardConfig(
        int width,
        int height,
        String primaryColor,
        String secondaryColor
) {
}
