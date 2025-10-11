package ru.nsu.babich;

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
     * Constructs adjacency list.
     */
    public AdjacencyList() {
        adjList = new HashMap<>();
    }

    @Override
    public void addVertex(Vertex vertex) {
        adjList.putIfAbsent(vertex, new HashSet<>());
    }

    @Override
    public void deleteVertex(Vertex vertex) {
        adjList.remove(vertex);
        for (Set<Vertex> neighbors : adjList.values()) {
            neighbors.remove(vertex);
        }
    }

    @Override
    public void addEdge(Edge edge) {
        addVertex(edge.from());
        addVertex(edge.to());
        adjList.get(edge.from()).add(edge.to());
    }

    @Override
    public void deleteEdge(Edge edge) {
        Set<Vertex> neighbors = adjList.get(edge.from());
        if (neighbors != null) {
            neighbors.remove(edge.to());
        }
    }

    @Override
    public List<Vertex> getVertexNeighbours(Vertex vertex) {
        Set<Vertex> neighbors = adjList.get(vertex);
        return neighbors != null ? new ArrayList<>(neighbors) : new ArrayList<>();
    }

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
