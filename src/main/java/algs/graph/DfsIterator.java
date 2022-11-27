package algs.graph;

import ds.graph.SimpleGraph;

import java.util.*;

public class DfsIterator<V> implements Iterator<V> {
    private final SimpleGraph<V> graph;
    private final int size;
    private final boolean[] visited;
    private int visitedCount;
    private final Deque<Integer> stack;

    @Override
    public String toString() {
        return "DfsIterator{" +
                "graph=" + graph +
                ", size=" + size +
                ", visited=" + Arrays.toString(visited) +
                ", visitedCount=" + visitedCount +
                ", stack=" + stack +
                '}';
    }

    public DfsIterator(final SimpleGraph<V> graph) {
        this.graph = graph;
        this.size = graph.vertexSize();
        visited = new boolean[size];
        visitedCount = 0;
        this.stack = new ArrayDeque<>();
        if (size > 0)
            this.stack.addLast(graph.getAnyVertexIndex());
    }

    @Override
    public boolean hasNext() {
        return visitedCount < size;
    }

    @Override
    public V next() {
        int node = stack.pollLast();
        if (visited[node]) {
            System.out.printf("Visited %d, continue to next...%n", node);
            return next();
        }
        visited[node] = true;
        visitedCount++;
        System.out.printf("Add to visited %d%n", node);
        for (int v: graph.getNeighborIndices(node)) {
            if (visited[v]) {
                continue;
            }
            System.out.printf("Expand from %d to %d%n", node, v);
            stack.addLast(v);
        }
        System.out.printf("Return node[%d] = %s%n", node, graph.getVertex(node));
        return graph.getVertex(node);
    }
}
