package pl.edu.agh.kt;

import org.jgrapht.Graph;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

public class GraphUtilsTesting {
    public static void test() {
        Graph<String, DefaultEdge> graph = new DefaultDirectedWeightedGraph<String, DefaultEdge>(DefaultEdge.class);
        GraphUtils.addLinkEdge(graph, "s1", "s2");
        GraphUtils.addLinkEdge(graph, "s1", "s3");
        GraphUtils.addLinkEdge(graph, "s3", "s4");
        GraphUtils.addLinkEdge(graph, "s2", "s4");
        GraphUtils.addLinkEdge(graph, "s5", "s4");

        List<String> sp = GraphUtils.calculatePath(graph, "s1", "s5");
        System.out.println(sp);
        GraphUtils.increaseEdgeWeight(graph, "s1", "s2");
        sp = GraphUtils.calculatePath(graph, "s1", "s5");
        System.out.println(sp);
    }

    public static void testSimpleVersion() {
        Graph<String, DefaultEdge> graph = new DefaultDirectedWeightedGraph<String, DefaultEdge>(DefaultEdge.class);
        GraphUtils.addLinkEdge(graph, "s1", "s2");
        GraphUtils.addLinkEdge(graph, "s2", "s1");
        GraphUtils.addLinkEdge(graph, "s2", "s3");
        GraphUtils.addLinkEdge(graph, "s3", "s2");
        GraphUtils.addLinkEdge(graph, "s1", "s3");
        GraphUtils.addLinkEdge(graph, "s3", "s1");

        List<String> sp;
        for(int i = 0; i <= 5; i++) {
            sp = GraphUtils.calculatePath(graph, "s1", "s3");
            System.out.println(sp);
        }
    }
}