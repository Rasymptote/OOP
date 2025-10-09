package ru.nsu.babich;

import java.util.List;

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
     * Reads edges from a file and then adds vertices and edges to the graph.
     *
     * @param filename The path to the file containing graph data.
     */
    void readFile(String filename);
}
