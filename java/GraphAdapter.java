import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.clique.DegeneracyBronKerboschCliqueFinder;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphAdapter {

    protected static final Logger logger = LoggerFactory.getLogger(SdnLabTopologyListener.class);

    public void countShortestPathsAfterUpdate(String vertex, Graph<String, DefaultEdge> topology) {
        for (DefaultEdge outgoingEdge : topology.outgoingEdgesOf(vertex)){
            logger.debug(BellmanFordShortestPath.findPathBetween(topology, vertex, topology.getEdgeTarget(outgoingEdge)).toString());
        }
    }
}
