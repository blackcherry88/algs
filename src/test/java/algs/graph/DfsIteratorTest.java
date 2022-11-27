package algs.graph;

import ds.graph.SimpleGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DfsIteratorTest {

    @Test
    void next() {
        final boolean directed = true;
        SimpleGraph<String> g = new SimpleGraph<>(directed);

        g.addEdge("A", "B");
        g.addEdge("B", "E");
        g.addEdge("B", "C");
        g.addEdge("B", "D");
        g.addEdge("B", "E");
        g.addEdge("C", "D");
        g.addEdge("D", "E");

        DfsIterator<String> iterator = new DfsIterator<>(g);
        System.out.println(iterator);
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}