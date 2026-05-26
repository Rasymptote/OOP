package ru.nsu.babich.client.dsl

import ru.nsu.babich.dsl.contracts.DslBuilder

class ClientConfigBuilder implements DslBuilder<ClientConfig> {
    private String locale = "en"
    private UiConfigBuilder uiConfigBuilder = new UiConfigBuilder()

    void locale(String locale) {
        this.locale = locale
    }

    void ui(Closure closure) {
        closure.delegate = uiConfigBuilder
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

    @Override
    void bind(Binding binding) {
        binding.setVariable("locale", this.&locale)
        binding.setVariable("ui", this.&ui)
    }

    @Override
    ClientConfig build() {
        return new ClientConfig(
                locale,
                uiConfigBuilder.build()
        )
    }
}
