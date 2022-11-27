package ds.graph;

import ds.util.ObjectIntMap;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleGraph<V> {

    private final ObjectIntMap<V> obj2Index;
    private final Map<Integer, Set<Integer>> g = new HashMap<>();
    private final boolean directed;

    public SimpleGraph(final boolean directed) {
        this.directed = directed;
        this.obj2Index = new ObjectIntMap<>();
    }

    public void addVertex(final V v) {
        Integer index = obj2Index.put(v);
        if (!g.containsKey(index)) {
            g.put(index, new LinkedHashSet<>());
        }
    }

    public List<V> getVertices() {
        return obj2Index.getIndices();
    }

    public V getVertex(int index) {
        return obj2Index.get(index);
    }

    public int getAnyVertexIndex() {
        if (obj2Index.size() > 0) {
            return 0;
        }
        return -1;
    }

    public Set<Integer> getNeighborIndices(int srcIndex) {
        return g.get(srcIndex);
    }

    public V getAnyVertex() {
        return obj2Index.keySet().iterator().next();
    }

    public List<V> getNeighbors(V v) {
        Integer index = obj2Index.getIndex(v);
        return g.get(index).stream().map(obj2Index::get).collect(Collectors.toList());
    }

    public void addEdge(final V source, final V destination) {
        addVertex(source);
        addVertex(destination);
        int srcIndex = obj2Index.getIndex(source);
        int dstIndex = obj2Index.getIndex(destination);

        g.get(srcIndex).add(dstIndex);
        if (!directed) {
            g.get(dstIndex).add(srcIndex);
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
        return obj2Index.contains(v);
    }

    public boolean hasEdge(final V source, final V destination) {
        int srcIndex = obj2Index.getIndex(source);
        int dstIndex = obj2Index.getIndex(destination);
        return g.get(srcIndex).contains(dstIndex);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(obj2Index).append("\n");
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
