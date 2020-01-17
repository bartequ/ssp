package ssp;

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

        List<String> sp = GraphUtils.bellamanFord(graph, "s1", "s5");
        System.out.println(sp);
        GraphUtils.increaseEdgeWeight(graph, "s1", "s2");
        sp = GraphUtils.bellamanFord(graph, "s1", "s5");
        System.out.println(sp);
    }
}