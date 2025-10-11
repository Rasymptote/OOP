package ru.nsu.babich.exceptions;

public class GraphCycleException extends GraphException {

    public GraphCycleException() {
        super("Cycle found.");
    }
}
