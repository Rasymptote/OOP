package ru.nsu.babich;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import ru.nsu.babich.exceptions.GraphException;

/**
 * Represents an unweighted oriented graph.
 */
public interface Graph {

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex The vertex to be added to the graph.
     */
    void addVertex(Vertex vertex);

    /**
     * Removes a vertex from the graph.
     *
     * @param vertex The vertex to be removed from the graph.
     */
    void deleteVertex(Vertex vertex);

    /**
     * Adds a directed edge to the graph.
     *
     * @param edge The edge to be added to the graph.
     */
    void addEdge(Edge edge);

    /**
     * Removes an edge from the graph.
     *
     * @param edge The edge to be removed from the graph.
     */
    void deleteEdge(Edge edge);

    /**
     * Returns a list of all neighboring vertices for the given vertex.
     *
     * @param vertex The vertex whose neighbors are to be returned.
     * @return A list of neighboring vertices.
     */
    List<Vertex> getVertexNeighbours(Vertex vertex);

    /**
     * Returns a list of all vertices in the graph.
     *
     * @return A list of all vertices.
     */
    List<Vertex> getVertices();

    /**
     * Reads edges from a file and then adds vertices and edges to the graph.
     *
     * @param filename The path to the file containing graph data.
     */
    default void readFile(String filename) {
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
            throw new GraphException(e.getMessage());
        }
    }

    /**
     * Sorts the graph's vertices in topological order.
     *
     * @return The list of graph vertices in topological order.
     */
    default List<Vertex> topologicalSort() {
        List<Vertex> vertices = getVertices();
        Map<Vertex, Integer> inDegree = new HashMap<>();
        Queue<Vertex> queue = new LinkedList<>();
        List<Vertex> result = new ArrayList<>();

        for (Vertex vertex : vertices) {
            inDegree.put(vertex, 0);
        }

        for (Vertex vertex : vertices) {
            for (Vertex neighbor : getVertexNeighbours(vertex)) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        for (Vertex vertex : vertices) {
            if (inDegree.get(vertex) == 0) {
                queue.offer(vertex);
            }
        }

        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            result.add(vertex);

            for (Vertex neighbor : getVertexNeighbours(vertex)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);

                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        if (result.size() != vertices.size()) {
            throw new GraphException("Graph contains a cycle. Topological sorting is impossible.");
        }
        return result;
    }
}
