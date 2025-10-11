package ru.nsu.babich;

class IncidenceMatrixTest extends GraphTest {

    @Override
    protected Graph createGraph() {
        return new IncidenceMatrix();
    }
}
