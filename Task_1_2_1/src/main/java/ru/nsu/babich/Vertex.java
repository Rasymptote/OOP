package ru.nsu.babich;

/**
 * Represents a vertex in a graph.
 *
 * @param id The unique identifier of the vertex.
 */
public record Vertex(String id) {

    @Override
    public String toString() {
        return this.id;
    }
}
