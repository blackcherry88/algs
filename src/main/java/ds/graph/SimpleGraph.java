package ds.graph;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleGraph<V> {

    private final Map<V, Set<V>> g = new HashMap<>();
    private final boolean directed;

    public SimpleGraph(final boolean directed) {
        this.directed = directed;
    }

    public void addVertex(final V v) {
        g.put(v, new LinkedHashSet<>());
    }

    public void addEdge(final V source, final V destination) {
        if (!g.containsKey(source)) {
            addVertex(source);
        }

        if (!g.containsKey(destination)) {
            addVertex(destination);
        }

        g.get(source).add(destination);
        if (!directed) {
            g.get(destination).add(source);
        }
    }

    public int edgeSize() {
        int size =  g.values().stream()
                .mapToInt(Set::size)
                .sum();
        if (!directed) {
            size /= 2;
        }
        return size;
    }

    public int vertexSize() {
        return g.size();
    }

    public boolean hasVertex(V v) {
        return g.containsKey(v);
    }

    public boolean hasEdge(final V source, final V destination) {
        return g.get(source).contains(destination);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for(var e : g.entrySet()) {
            builder.append(e.getKey().toString()).append(": ");
            var vString = e.getValue().stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
            builder.append(vString);
            builder.append("\n");
        }

        return (builder.toString());
    }
}
