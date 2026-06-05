package ru.nsu.babich.client.dsl

import ru.nsu.babich.dsl.contracts.Builder

class I18nConfigBuilder implements Builder<I18nConfig> {
    private LocaleTextBuilder ruBuilder = new LocaleTextBuilder()
    private LocaleTextBuilder enBuilder = new LocaleTextBuilder()

    void ru(Closure closure) {
        closure.delegate = ruBuilder
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

    void en(Closure closure) {
        closure.delegate = enBuilder
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

    @Override
    I18nConfig build() {
        return new I18nConfig(
                Map.of(
                        "ru", ruBuilder.build(),
                        "en", enBuilder.build()
                )
        )
    }
}
