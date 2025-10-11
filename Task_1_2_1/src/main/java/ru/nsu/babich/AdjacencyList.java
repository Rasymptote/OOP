package ru.nsu.babich;

import ru.nsu.babich.exceptions.GraphEdgeException;
import ru.nsu.babich.exceptions.GraphVertexException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represents a graph via adjacency list.
 */
public class AdjacencyList implements Graph {
    private final Map<Vertex, Set<Vertex>> adjList;

    /**
     * Constructs an adjacency list.
     */
    public AdjacencyList() {
        adjList = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addVertex(Vertex vertex) {
        adjList.putIfAbsent(vertex, new HashSet<>());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteVertex(Vertex vertex) {
        var removed = adjList.remove(vertex);
        if (removed == null) {
            throw new GraphVertexException(vertex);
        }
        for (Set<Vertex> neighbors : adjList.values()) {
            neighbors.remove(vertex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEdge(Edge edge) {
        addVertex(edge.from());
        addVertex(edge.to());
        adjList.get(edge.from()).add(edge.to());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteEdge(Edge edge) {
        Set<Vertex> neighbors = adjList.get(edge.from());
        if (neighbors != null) {
            var removed = neighbors.remove(edge.to());
            if (!removed) {
                throw new GraphEdgeException(edge);
            }
        } else {
            throw new GraphEdgeException(edge);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Vertex> getVertexNeighbours(Vertex vertex) {
        Set<Vertex> neighbors = adjList.get(vertex);
        if (neighbors == null) {
            throw new GraphVertexException(vertex);
        }
        return new ArrayList<>(neighbors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Vertex> getVertices() {
        return new ArrayList<>(adjList.keySet());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex vertex : adjList.keySet()) {
            sb.append(String.format("%s -> %s\n", vertex, adjList.get(vertex)));
        }
        return sb.toString();
    }
}
