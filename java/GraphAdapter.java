package pl.edu.agh.kt;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.clique.DegeneracyBronKerboschCliqueFinder;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphAdapter {

    protected static final Logger logger = LoggerFactory.getLogger(GraphAdapter.class);

    public void countShortestPathsAfterUpdate(String vertex, Graph<String, DefaultEdge> topology) {
    	logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2 ");
        for (DefaultEdge outgoingEdge : topology.outgoingEdgesOf(vertex)){
        	logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@3");
        	GraphPath graphPath = BellmanFordShortestPath.findPathBetween(topology, vertex, topology.getEdgeTarget(outgoingEdge));
        	logger.debug("Graph vertex start: {} ", vertex);
        	for (Object vertName : graphPath.getVertexList()) {
        		logger.debug("Graph vertex: {} ", vertName.toString());
        	}
            
        }
    }
}