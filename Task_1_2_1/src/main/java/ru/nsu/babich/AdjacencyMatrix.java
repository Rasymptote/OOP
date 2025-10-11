package ru.nsu.babich;

import java.util.ArrayList;
import java.util.List;
import ru.nsu.babich.exceptions.GraphEdgeException;
import ru.nsu.babich.exceptions.GraphVertexException;

/**
 * Represents a graph via adjacency matrix.
 */
public class AdjacencyMatrix implements Graph {
    private final Matrix adjMatrix;
    private final ArrayList<Vertex> vertices;

    /**
     * Constructs an adjacency matrix.
     */
    public AdjacencyMatrix() {
        this.adjMatrix = new Matrix();
        this.vertices = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addVertex(Vertex vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            adjMatrix.addRow();
            adjMatrix.addCol();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteVertex(Vertex vertex) {
        int index = vertices.indexOf(vertex);
        if (index == -1) {
            throw new GraphVertexException(vertex);
        }
        vertices.remove(vertex);
        adjMatrix.removeRow(index);
        adjMatrix.removeCol(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEdge(Edge edge) {
        addVertex(edge.from());
        addVertex(edge.to());
        int fromIndex = vertices.indexOf(edge.from());
        int toIndex = vertices.indexOf(edge.to());
        adjMatrix.set(fromIndex, toIndex, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteEdge(Edge edge) {
        int fromIndex = vertices.indexOf(edge.from());
        int toIndex = vertices.indexOf(edge.to());
        if (fromIndex == -1 || toIndex == -1) {
            throw new GraphEdgeException(edge);
        }
        adjMatrix.set(fromIndex, toIndex, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Vertex> getVertexNeighbours(Vertex vertex) {
        int index = vertices.indexOf(vertex);
        if (index == -1) {
            throw new GraphVertexException(vertex);
        }
        ArrayList<Vertex> neighbours = new ArrayList<>();
        for (int col = 0; col < adjMatrix.getWidth(); col++) {
           if (adjMatrix.get(index, col) == 1) {
               neighbours.add(vertices.get(col));
           }
        }
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Vertex> getVertices() {
        return new ArrayList<>(vertices);
    }

    @Override
    public String toString() {
        return adjMatrix.toString();
    }
}
