package bellman;


import org.jgrapht.Graph;
import org.psjava.algo.graph.shortestpath.BellmanFordAlgorithm;
import org.psjava.algo.graph.shortestpath.SingleSourceShortestPathResult;
import org.psjava.ds.graph.DirectedWeightedEdge;
import org.psjava.ds.graph.MutableDirectedWeightedGraph;
import org.psjava.ds.numbersystrem.IntegerNumberSystem;

import java.util.List;

public class GraphAdapter {
//    public List<String> calculateShortestPath(Graph graph) {}

    private void countShortestPathsAfterUpdate(ILinkDiscovery.LDUpdate update, TopologyFromController topology) {
        MutableDirectedWeightedGraph<String, Integer> graph = MutableDirectedWeightedGraph.create();
        for (Edge edge : topology.getAllEdges) {
            graph.addEdge(edge.getSource, edge.getDest, edge.getCost);
        }

        BellmanFordAlgorithm algorithm = BellmanFordAlgorithm.getInstance();
        SingleSourceShortestPathResult<String, Integer, DirectedWeightedEdge<String, Integer>> result = algorithm.calc(graph, update. , IntegerNumberSystem.getInstance());
    }
}
