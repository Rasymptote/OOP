package ru.nsu.babich.dsl;

import groovy.lang.Binding;
import ru.nsu.babich.dsl.contracts.Configurable;
import ru.nsu.babich.dsl.contracts.DslBuilder;

public class Environment<T extends Configurable> {
    public final Binding binding = new Binding();
    private final DslBuilder<T> builder;

    public Environment(DslBuilder<T> builder) {
        builder.bind(binding);
        this.builder = builder;
    }

    public T extract() {
        return builder.build();
    }
}
