package ru.nsu.babich;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AdjacencyMatrixTest extends GraphTest {

    @Override
    protected Graph createGraph() {
        return new AdjacencyMatrix();
    }

    @Test
    void checkToString() {
        Graph adjMatrix = createGraph();
        var a = new Vertex("a");
        var b = new Vertex("b");
        var c = new Vertex("c");

        adjMatrix.addEdge(new Edge(a, c));
        adjMatrix.addEdge(new Edge(c, b));
        adjMatrix.addEdge(new Edge(b, b));

        assertEquals("""
                        0 1 0\s
                        0 0 1\s
                        0 0 1\s
                        """,
                adjMatrix.toString()
        );
    }
}
