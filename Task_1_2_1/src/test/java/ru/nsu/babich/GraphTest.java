package ru.nsu.babich;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.babich.exceptions.GraphCycleException;
import ru.nsu.babich.exceptions.GraphEdgeException;
import ru.nsu.babich.exceptions.GraphVertexException;
import java.util.List;

abstract class GraphTest {
    protected Graph graph;

    protected abstract Graph createGraph();

    @BeforeEach
    void setUp() {
        graph = createGraph();
    }

    @Test
    void checkAddAndRemoveVertices() {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");

        graph.addVertex(v1);
        graph.addVertex(v2);

        List<Vertex> vertices = graph.getVertices();
        assertEquals(2, vertices.size());
        assertTrue(vertices.contains(v1));
        assertTrue(vertices.contains(v2));

        graph.deleteVertex(v1);
        vertices = graph.getVertices();
        assertEquals(1, vertices.size());
        assertFalse(vertices.contains(v1));
        assertTrue(vertices.contains(v2));
    }

    @Test
    void checkAddAndRemoveEdges() {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");
        Edge e1 = new Edge(v1, v2);
        Edge e2 = new Edge(v2, v3);

        graph.addEdge(e1);
        graph.addEdge(e2);

        List<Vertex> neighbors1 = graph.getVertexNeighbours(v1);
        assertEquals(1, neighbors1.size());
        assertTrue(neighbors1.contains(v2));

        List<Vertex> neighbors2 = graph.getVertexNeighbours(v2);
        assertEquals(1, neighbors2.size());
        assertTrue(neighbors2.contains(v3));

        graph.deleteEdge(e1);
        neighbors1 = graph.getVertexNeighbours(v1);
        assertEquals(0, neighbors1.size());

        neighbors2 = graph.getVertexNeighbours(v2);
        assertEquals(1, neighbors2.size());
    }

    @Test
    void checkNeighbourhood() {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");
        Vertex v4 = new Vertex("D");

        graph.addEdge(new Edge(v1, v2));
        graph.addEdge(new Edge(v1, v3));
        graph.addEdge(new Edge(v2, v4));
        graph.addEdge(new Edge(v3, v4));

        List<Vertex> neighborsA = graph.getVertexNeighbours(v1);
        assertEquals(2, neighborsA.size());
        assertTrue(neighborsA.contains(v2));
        assertTrue(neighborsA.contains(v3));

        List<Vertex> neighborsB = graph.getVertexNeighbours(v2);
        assertEquals(1, neighborsB.size());
        assertTrue(neighborsB.contains(v4));

        List<Vertex> neighborsC = graph.getVertexNeighbours(v3);
        assertEquals(1, neighborsC.size());
        assertTrue(neighborsC.contains(v4));

        List<Vertex> neighborsD = graph.getVertexNeighbours(v4);
        assertEquals(0, neighborsD.size());
    }

    @Test
    void checkTopologicalSort() {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");

        graph.addEdge(new Edge(v1, v2));
        graph.addEdge(new Edge(v2, v3));

        List<Vertex> sorted = graph.topologicalSort();
        assertEquals(3, sorted.size());

        int indexA = sorted.indexOf(v1);
        int indexB = sorted.indexOf(v2);
        int indexC = sorted.indexOf(v3);

        assertTrue(indexA < indexB);
        assertTrue(indexB < indexC);
    }

    @Test
    void checkCycleDetection() {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");

        graph.addEdge(new Edge(v1, v2));
        graph.addEdge(new Edge(v2, v3));
        graph.addEdge(new Edge(v3, v1));

        assertThrows(GraphCycleException.class, () -> graph.topologicalSort());
    }

    @Test
    void checkRemoveNonExistentEdge() {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Edge edge = new Edge(v1, v2);

        assertThrows(GraphEdgeException.class, () -> graph.deleteEdge(edge));
    }

    @Test
    void checkGetNeighborsNonExistentVertex() {
        Vertex v1 = new Vertex("A");

        assertThrows(GraphVertexException.class, () -> graph.getVertexNeighbours(v1));
    }

    @Test
    void checkDuplicateVertices() {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("A");

        graph.addVertex(v1);
        graph.addVertex(v2);

        List<Vertex> vertices = graph.getVertices();
        assertEquals(1, vertices.size());
    }
}
