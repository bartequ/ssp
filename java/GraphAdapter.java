package pl.edu.agh.kt;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.clique.DegeneracyBronKerboschCliqueFinder;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GraphAdapter {

    protected static final Logger logger = LoggerFactory.getLogger(GraphAdapter.class);

    public GraphPath<String, DefaultEdge> countShortestPathsAfterUpdate(Graph<String, DefaultEdge> topology, String startVertex, String endVertex) {
        GraphPath<String, DefaultEdge> graphPath = BellmanFordShortestPath.findPathBetween(topology, startVertex, endVertex);
//        logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Weight for " + startVertex + "-" + endVertex + "is" + topology.getEdgeWeight(topology.getEdge(startVertex, endVertex)));
        List<String> intList = graphPath.getVertexList();
        String nodeList = intList.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining("-", "{", "}"));
        logger.error("@@@@@@@@###########################@@@@@@@@@@@@@@@@@@@@@@@Counted shortest path from "+ startVertex + "to "+ endVertex + ":" + nodeList);
        
        return graphPath;
    }
}	