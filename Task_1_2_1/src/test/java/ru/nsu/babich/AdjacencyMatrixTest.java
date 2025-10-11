package ru.nsu.babich;

class AdjacencyMatrixTest extends GraphTest {

    @Override
    protected Graph createGraph() {
        return new AdjacencyMatrix();
    }
}
