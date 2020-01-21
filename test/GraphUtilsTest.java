package ssp;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GraphUtilsTest {

    @Test
    public void caluclate_path_should_return_proper_path() {
        Graph<String, DefaultEdge> graph = new DefaultDirectedWeightedGraph<String, DefaultEdge>(DefaultEdge.class);
        GraphUtils.addLinkEdge(graph, "s1", "s2");
        GraphUtils.addLinkEdge(graph, "s1", "s3");
        GraphUtils.addLinkEdge(graph, "s3", "s4");
        GraphUtils.addLinkEdge(graph, "s2", "s4");
        GraphUtils.addLinkEdge(graph, "s5", "s4");
        GraphUtils.addLinkEdge(graph, "s5", "s3");

        List<String> sp = GraphUtils.calculatePath(graph, "s1", "s5");

        assertEquals(sp, Arrays.asList("s1", "s3", "s5"));
    }

    @Test
    public void calculate_path_should_return_empty_path_when_grap_is_not_coherent() {
        Graph<String, DefaultEdge> graph = new DefaultDirectedWeightedGraph<String, DefaultEdge>(DefaultEdge.class);

        GraphUtils.addLinkEdge(graph, "s1", "s2");
        GraphUtils.addLinkEdge(graph, "s4", "s3");

        List<String> sp = GraphUtils.calculatePath(graph, "s1", "s3");
        assertEquals(sp, new ArrayList<String>());
    }

    @Test
    public void caluclate_path_should_return_proper_path_in_weighted_graph() {
        Graph<String, DefaultEdge> graph = new DefaultDirectedWeightedGraph<String, DefaultEdge>(DefaultEdge.class);
        GraphUtils.addLinkEdge(graph, "s1", "s2");
        GraphUtils.addLinkEdge(graph, "s4", "s3");
        GraphUtils.addLinkEdge(graph, "s1", "s3");
        GraphUtils.addLinkEdge(graph, "s5", "s2");
        GraphUtils.addLinkEdge(graph, "s5", "s4");

        GraphUtils.increaseEdgeWeight(graph, "s1", "s3");
        GraphUtils.increaseEdgeWeight(graph, "s1", "s3");

        List<String> sp = GraphUtils.calculatePath(graph, "s1", "s3");

        assertEquals(sp, Arrays.asList("s1", "s2", "s5", "s4", "s3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void caluclate_path_should_throw_exception_when_graph_not_contains_end_node() {
        Graph<String, DefaultEdge> graph = new DefaultDirectedWeightedGraph<String, DefaultEdge>(DefaultEdge.class);
        GraphUtils.addLinkEdge(graph, "s1", "s2");
        GraphUtils.addLinkEdge(graph, "s4", "s3");
        GraphUtils.addLinkEdge(graph, "s1", "s3");

        GraphUtils.calculatePath(graph, "s1", "s5");
    }
}