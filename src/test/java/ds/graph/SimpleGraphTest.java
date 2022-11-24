package ds.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleGraphTest {

    @Test
    public void testSimpleGraph() {
        final boolean directed = true;
        SimpleGraph<Integer> g = new SimpleGraph<>(directed);

        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);

        assertEquals(g.vertexSize(), 5);
        assertEquals(g.edgeSize(), 7);
        assert(g.hasVertex(1));
        assert(g.hasEdge(0, 1));

        System.out.println(g);
    }

}