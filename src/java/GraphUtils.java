package ssp;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GraphUtils {
    private final static double weightIncrementor = 10;
    private static PathMatch pathSwitch = PathMatch.PATH_SHORT;

    public static List<String> calculatePath(Graph<String, DefaultEdge> graph, String start, String end) {
        GraphPath<String, DefaultEdge> graphPath = BellmanFordShortestPath.findPathBetween(graph, start, end);

        List<String> nodeList = new ArrayList<String>();;
        if(graphPath != null) {
            nodeList = graphPath.getVertexList();
        }
        //TODO: ADD LOGG
        return nodeList;

//        switch (pathSwitch) {
//            case PATH_SHORT:
//                pathSwitch = PathMatch.PATH_MID;
//                return Arrays.asList("S1", "S3");
//            default:
//                pathSwitch = PathMatch.PATH_SHORT;
//                return Arrays.asList("S1", "S2", "S3");
//        }
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
        if(!graph.containsVertex(source.toString())) {
            graph.addVertex(source.toString());
            // logger.debug(update.getSrc().toString());
        }
        if (!graph.containsVertex(dest.toString())) {
            graph.addVertex(dest.toString());
            // logger.debug(update.getDst().toString());
        }
        graph.addEdge(source.toString(), dest.toString());
        graph.addEdge(dest.toString(), source.toString());
    }
}


enum PathMatch {
    PATH_SHORT,
    PATH_MID
}