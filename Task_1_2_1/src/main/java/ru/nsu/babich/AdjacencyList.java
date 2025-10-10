package ru.nsu.babich;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
        adjList.putIfAbsent(vertex, new TreeSet<>(VERTEX_COMPARATOR));
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
    public void readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                if (tokens.length == 2) {
                    Vertex vertex1 = new Vertex(tokens[0]);
                    Vertex vertex2 = new Vertex(tokens[1]);
                    addVertex(vertex1);
                    addVertex(vertex2);
                    addEdge(new Edge(vertex1, vertex2));
                }
            }
        } catch (IOException e) {
            throw new GraphReadException(e.getMessage());
        }
    }

    private static final Comparator<Vertex> VERTEX_COMPARATOR =
            Comparator.comparing(Vertex::id);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Vertex vertex : adjList.keySet().stream()
                .sorted(VERTEX_COMPARATOR)
                .toList()) {

            sb.append(String.format("%s -> %s\n", vertex, adjList.get(vertex)));
        }
        return sb.toString();
    }
}
