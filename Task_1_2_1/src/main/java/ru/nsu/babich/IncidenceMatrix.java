package ru.nsu.babich;

import ru.nsu.babich.exceptions.GraphEdgeException;
import ru.nsu.babich.exceptions.GraphVertexException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a graph via incidence matrix.
 */
public class IncidenceMatrix implements Graph {
    private final Matrix incMatrix;
    private final ArrayList<Vertex> vertices;

    /**
     * Constructs an incidence matrix.
     */
    public IncidenceMatrix() {
        this.incMatrix = new Matrix();
        this.vertices = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addVertex(Vertex vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            incMatrix.addRow();
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
        for (int col = incMatrix.getWidth() - 1; col >= 0; col--) {
            if (incMatrix.get(index, col) != 0) {
                incMatrix.removeCol(col);
            }
        }
        incMatrix.removeRow(index);
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
        for (int col = 0; col < incMatrix.getWidth(); col++) {
            if (fromIndex == toIndex && incMatrix.get(fromIndex, col) == 1) {
                return;
            }
            if (incMatrix.get(fromIndex, col) == -1 && incMatrix.get(toIndex, col) == 1) {
                return;
            }
        }
        incMatrix.addCol();
        if (fromIndex == toIndex) {
            incMatrix.set(fromIndex, incMatrix.getWidth() - 1, 1);
        } else {
            incMatrix.set(fromIndex, incMatrix.getWidth() - 1, -1);
            incMatrix.set(toIndex, incMatrix.getWidth() - 1, 1);
        }
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
        for (int col = 0; col < incMatrix.getWidth(); col++) {
            if (incMatrix.get(fromIndex, col) == -1 && incMatrix.get(toIndex, col) == 1) {
                incMatrix.removeCol(col);
            }
            if (fromIndex == toIndex && incMatrix.get(fromIndex, col) == 1) {
                incMatrix.removeCol(col);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Vertex> getVertexNeighbours(Vertex vertex) {
        int index = vertices.indexOf(vertex);
        if (index == -1) {
            throw new GraphVertexException(vertex);
        }
        ArrayList<Vertex> neighbours = new ArrayList<>();
        for (int col = 0; col < incMatrix.getWidth(); col++) {
            if (incMatrix.get(index, col) == -1) {
                for (int row = 0; row < incMatrix.getHeight(); row++) {
                    if (incMatrix.get(row, col) == 1) {
                        neighbours.add(vertices.get(row));
                    }
                }
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
        return incMatrix.toString();
    }
}
