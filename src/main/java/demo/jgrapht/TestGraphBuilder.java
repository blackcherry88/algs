package demo.jgrapht;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.builder.GraphBuilder;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.Arrays;

public class TestGraphBuilder {

    public static void main(String[] args) {

        var kiteGraph = buildKiteGraph();
        System.out.println("KiteGraph is " + kiteGraph);
        var iter = new DepthFirstIterator<>(kiteGraph);
        while (iter.hasNext()) {
            var vertex = iter.next();
            System.out.println("Vertex " + vertex + kiteGraph.edgesOf(vertex));
        }
    }

    private static Graph<Integer, DefaultEdge> buildEmptySimpleGraph() {
        return GraphTypeBuilder
                .<Integer, DefaultEdge> undirected()
                .allowingMultipleEdges(false)
                .allowingSelfLoops(false)
                .edgeClass(DefaultEdge.class)
                .weighted(false)
                .buildGraph();
    }

    private static Graph<Integer, DefaultEdge> buildKiteGraph()
    {
        return new GraphBuilder<>(buildEmptySimpleGraph())
                .addEdgeChain(1, 2, 3, 4, 1).addEdge(2, 4).addEdge(3, 5).buildAsUnmodifiable();
    }
}
