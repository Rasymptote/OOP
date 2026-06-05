package ru.nsu.babich.dsl.contracts;

public interface DslBuilder<T extends Configurable> extends Bindable, Builder<T> {
}
