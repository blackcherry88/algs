package ds.graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleGraphTest {

    protected static SimpleGraph<String> createGraph() {
        final boolean directed = true;
        SimpleGraph<String> g = new SimpleGraph<>(directed);

        g.addEdge("A", "B");
        g.addEdge("B", "E");
        g.addEdge("B", "C");
        g.addEdge("B", "D");
        g.addEdge("C", "D");
        g.addEdge("D", "E");
        return g;
    }

    @Test
    void getAnyVertexIndex() {
        SimpleGraph<String> g = createGraph();
        int nodeIndex = g.getAnyVertexIndex();
        System.out.println(nodeIndex);
        assert (nodeIndex >= 0);
    }

    @Test
    void getAnyVertex() {
        SimpleGraph<String> g = createGraph();
        String node = g.getAnyVertex();
        System.out.println(node);
        List<String> nodes = List.of("A", "B", "C", "D", "E");
        assert(nodes.contains(node));
    }

    @Test
    void addEdge() {
        SimpleGraph<String> g = createGraph();

        assertEquals(g.vertexSize(), 5);
        assertEquals(g.edgeSize(), 6);
        assert(g.hasVertex("B"));
        assert(g.hasEdge("A", "B"));

        System.out.println(g);
    }
}