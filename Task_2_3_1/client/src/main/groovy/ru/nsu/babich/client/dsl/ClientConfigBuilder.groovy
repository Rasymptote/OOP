package ru.nsu.babich.client.dsl

import ru.nsu.babich.dsl.contracts.DslBuilder

class ClientConfigBuilder implements DslBuilder<ClientConfig> {
    private String locale = "en"
    private UiConfigBuilder uiConfigBuilder = new UiConfigBuilder()
    private I18nConfigBuilder i18nConfigBuilder = new I18nConfigBuilder()

    void locale(String locale) {
        this.locale = locale
    }

    void ui(Closure closure) {
        closure.delegate = uiConfigBuilder
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

    void i18n(Closure closure) {
        closure.delegate = i18nConfigBuilder
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

    @Override
    void bind(Binding binding) {
        binding.setVariable("locale", this.&locale)
        binding.setVariable("ui", this.&ui)
        binding.setVariable("i18n", this.&i18n)
    }

    @Override
    ClientConfig build() {
        return new ClientConfig(
                locale,
                uiConfigBuilder.build(),
                i18nConfigBuilder.build()
        )
    }
}
