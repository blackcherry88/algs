package demo.jgrapht;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;
import org.jgrapht.traverse.DepthFirstIterator;

import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.server.ExportException;
import java.util.LinkedHashMap;
import java.util.Map;


public class HelloWorld {

    public static void main(String[] args) throws URISyntaxException, ExportException {
        var g = createStringGraph();
        System.out.println("--- toString output");
        System.out.println(g);
        System.out.println();

        Graph<URI, DefaultEdge> hrefGRaph = createHrefGraph();

        URI start = hrefGRaph.vertexSet()
                .stream().filter(uri -> uri.getHost().equals("www.jgrapht.org"))
                .findAny().orElse(null);

        System.out.println("Try traverse from " + start);
        var iterator = new DepthFirstIterator<>(hrefGRaph, start);
        while (iterator.hasNext()) {
            var uri = iterator.next();
            System.out.println(uri);
        }

        renderHrefGraph(hrefGRaph);
    }

    private static void renderHrefGraph(final Graph<URI, DefaultEdge> hrefGraph) {

        final DOTExporter<URI, DefaultEdge> exporter =
                new DOTExporter<>(v -> v.getHost().replace('.', '_'));
        exporter.setVertexAttributeProvider((v) -> {
            Map<String, Attribute> map = new LinkedHashMap<>();
            map.put("label", DefaultAttribute.createAttribute(v.toString()));
            return map;
        });

        final Writer writer = new StringWriter();
        exporter.exportGraph(hrefGraph, writer);
        System.out.println(writer);
    }

    public static Graph<String, DefaultEdge> createStringGraph() {
        final Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
        final String[] vertices = {
                "v1", "v2", "v3", "v4"
        };
        for (var v: vertices) {
            g.addVertex(v);
        }

        g.addEdge("v1", "v2");
        g.addEdge("v2", "v3");
        g.addEdge("v3", "v4");

        return g;
    }

    public static Graph<URI, DefaultEdge> createHrefGraph() throws URISyntaxException {

        final Graph<URI, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

        final URI google = new URI("http://www.google.com");
        final URI wiki = new URI("http://www.wikipedia.org");
        final URI jgrapht = new URI("http://www.jgrapht.org");

        g.addVertex(google);
        g.addVertex(wiki);
        g.addVertex(jgrapht);

        g.addEdge(jgrapht, wiki);
        g.addEdge(google, jgrapht);
        g.addEdge(google, wiki);
        g.addEdge(wiki, google);

        return g;
    }

}
