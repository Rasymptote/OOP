package ru.nsu.babich;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AdjacencyListTest extends GraphTest {

    @Override
    protected Graph createGraph() {
        return new AdjacencyList();
    }

    @Test
    void checkToString() {
        Graph adjList = createGraph();
        var a = new Vertex("a");
        var b = new Vertex("b");
        var c = new Vertex("c");

        adjList.addEdge(new Edge(a, c));
        adjList.addEdge(new Edge(c, b));
        adjList.addEdge(new Edge(b, b));

        assertEquals("""
                    a -> [c]
                    b -> [b]
                    c -> [b]
                    """,
                adjList.toString()
        );
    }
}
