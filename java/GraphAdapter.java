import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.clique.DegeneracyBronKerboschCliqueFinder;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.DefaultEdge;

public class GraphAdapter {
    public void countShortestPathsAfterUpdate(String vertex, Graph<String, DefaultEdge> topology) {
        for (DefaultEdge outgoingEdge : topology.outgoingEdgesOf(vertex)){
            BellmanFordShortestPath.findPathBetween(topology, vertex, topology.getEdgeTarget(outgoingEdge));
        }
    }
}
