package ru.nsu.babich;

/**
 * Represents a directed edge in a graph.
 *
 * @param from The source vertex of the edge.
 * @param to The destination vertex of the edge.
 */
public record Edge(Vertex from, Vertex to) {

    @Override
    public String toString() {
        return String.format("(%s;%s)\n", from, to);
    }
}
