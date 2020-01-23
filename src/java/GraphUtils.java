package pl.edu.agh.kt;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;




public class GraphUtils {
    private final static double weightIncrementor = 10;
    private static PathMatch pathSwitch = PathMatch.PATH_SHORT;
    
    protected static Logger logger = LoggerFactory.getLogger(GraphUtils.class);

    public static List<String> calculatePath(Graph<String, DefaultEdge> graph, String start, String end) {
        GraphPath<String, DefaultEdge> graphPath = BellmanFordShortestPath.findPathBetween(graph, start, end);
        List<String> nodeList = graphPath.getVertexList();
        String nodes = nodeList.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining("-", "{", "}"));
        logger.debug("Counted shortest path from " + start + " to "+ end + ":" + nodes);
        return nodeList;

        /*switch (pathSwitch) {
            case PATH_SHORT:
                pathSwitch = PathMatch.PATH_MID;
                return Arrays.asList("S1", "S3", "H2");
            default:
                pathSwitch = PathMatch.PATH_SHORT;
                return Arrays.asList("S1", "S2", "S3", "H2");
        }*/
    }

    public static boolean increaseEdgeWeight(Graph<String, DefaultEdge> graph, String node1, String node2) {
        DefaultEdge edge = graph.getEdge(node1, node2);
        DefaultEdge reverseEdge = graph.getEdge(node2, node1);
        if(edge != null && reverseEdge != null) {
            double currentWeight = graph.getEdgeWeight(edge);
            graph.setEdgeWeight(edge, currentWeight + weightIncrementor);
            graph.setEdgeWeight(reverseEdge, currentWeight + weightIncrementor);
            return true;
        } else {
            return false;
        }
    }

    public static void addLinkEdge(Graph<String, DefaultEdge> graph, Object source, Object dest) {
        //TODO: Uncomment LOGGING
        if(!graph.containsVertex(source.toString().toUpperCase())) {
            graph.addVertex(source.toString().toUpperCase());
//             logger.debug(update.getSrc().toString());
        }
        if (!graph.containsVertex(dest.toString())) {
            graph.addVertex(dest.toString().toUpperCase());
//             logger.debug(update.getDst().toString());
        }
        graph.addEdge(source.toString().toUpperCase(), dest.toString().toUpperCase());
        graph.addEdge(dest.toString().toUpperCase(), source.toString().toUpperCase());
    }
}


enum PathMatch {
    PATH_SHORT,
    PATH_MID
}