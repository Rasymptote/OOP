package ru.nsu.babich.client.dsl;

import java.util.Map;

public record I18nConfig(Map<String, LocaleTextConfig> locales) {
    public LocaleTextConfig resolve(String locale) {
        if (locales == null || locales.isEmpty()) {
            return null;
        }
        if (locale != null && locales.containsKey(locale)) {
            return locales.get(locale);
        }
        if (locales.containsKey("en")) {
            return locales.get("en");
        }
        return locales.values().stream().findFirst().orElse(null);
    }
}
