package ru.nsu.babich;

class AdjacencyListTest extends GraphTest {

    @Override
    protected Graph createGraph() {
        return new AdjacencyList();
    }
}
