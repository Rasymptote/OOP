package ru.nsu.babich.client.dsl;

import ru.nsu.babich.dsl.contracts.Configurable;

public record ClientConfig(
        String locale,
        UiConfig ui,
        I18nConfig i18n
) implements Configurable {
}
