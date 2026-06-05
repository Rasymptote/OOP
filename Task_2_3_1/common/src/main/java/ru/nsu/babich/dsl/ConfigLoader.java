package ru.nsu.babich.dsl;

import groovy.lang.GroovyShell;
import java.io.IOException;
import java.nio.file.Path;
import ru.nsu.babich.dsl.contracts.DslBuilder;
import ru.nsu.babich.dsl.contracts.Configurable;

public class ConfigLoader<T extends Configurable> {
    private final DslBuilder<T> builder;

    public ConfigLoader(DslBuilder<T> builder) {
        this.builder = builder;
    }

    public T load(Path path) throws IOException {
        var environment = new Environment<>(builder);
        var shell = new GroovyShell(environment.binding);
        shell.evaluate(path.toFile());

        return environment.extract();
    }
}
