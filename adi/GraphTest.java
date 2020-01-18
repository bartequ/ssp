import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;

public class GraphTest {

    public static void main(String[] args) {

        DefaultDirectedWeightedGraph<String, DefaultEdge> graph = GraphHolder.getInstance();
        DefaultDirectedWeightedGraph<String, DefaultEdge> graph1 = GraphHolder.getInstance();

        graph.addVertex("S1");
        graph.addVertex("SS1");
        graph.addEdge("S1", "SS1");
        graph.addEdge("SS1", "S1");

        graph1.addVertex("S2");

        graph.getEdge("S1", "SS1");

        System.out.println(graph == graph1);
    }
}
