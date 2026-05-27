package ru.nsu.babich.client.dsl

import ru.nsu.babich.dsl.contracts.Builder

class LocaleTextBuilder implements Builder<LocaleTextConfig> {
    private String windowTitle
    private MenuTextBuilder menuTextBuilder = new MenuTextBuilder()

    void windowTitle(String windowTitle) {
        this.windowTitle = windowTitle
    }

    void menu(Closure closure) {
        closure.delegate = menuTextBuilder
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

    @Override
    LocaleTextConfig build() {
        return new LocaleTextConfig(
                windowTitle,
                menuTextBuilder.build()
        )
    }
}
