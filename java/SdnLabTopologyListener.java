package pl.edu.agh.kt;

import java.util.List;
import java.util.Set;

import org.apache.derby.impl.sql.execute.UpdateConstantAction;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import net.floodlightcontroller.linkdiscovery.ILinkDiscovery;
import net.floodlightcontroller.linkdiscovery.ILinkDiscovery.LDUpdate;
import net.floodlightcontroller.topology.ITopologyListener;

public class SdnLabTopologyListener implements ITopologyListener {

    protected static final Logger logger = LoggerFactory.getLogger(SdnLabTopologyListener.class);
    //protected Topology topology = new Topology(); - niepotrzebna
    protected Graph<String, DefaultEdge> graph;
    protected pl.edu.agh.kt.GraphAdapter graphAdapter = new pl.edu.agh.kt.GraphAdapter();

    public SdnLabTopologyListener() {
        graph = new DefaultDirectedGraph<>(DefaultEdge.class);
    }

    @Override
    public void topologyChanged(List<LDUpdate> linkUpdates) {
        logger.debug("Received topology status");
        for (ILinkDiscovery.LDUpdate update : linkUpdates) {
            switch (update.getOperation()) {
                case LINK_UPDATED:
                    logger.debug("Link updated. Update {}", update.toString());
                    if (!graph.containsVertex(update.getSrc().toString())) {
                        graph.addVertex(update.getSrc().toString());
                    }
                    if (!graph.containsVertex(update.getDst().toString())) {
                        graph.addVertex(update.getDst().toString());
                    }
                    graph.addEdge(update.getSrc().toString(), update.getDst().toString());
					Set<String> vertexSet = graph.vertexSet();
                    for (String startVertex : vertexSet) {
                        for (String endVertex : vertexSet)
							if (!startVertex.equals(endVertex)) {
                                GraphPath<String, DefaultEdge> graphPath = graphAdapter.countShortestPathsAfterUpdate(graph, startVertex, endVertex);
							}
					}
                    break;
                case LINK_REMOVED:
                    logger.debug("Link removed. Update {}", update.toString());
                    graph.removeEdge(update.getSrc().toString(), update.getDst().toString());
                    break;
                case SWITCH_UPDATED:
                    logger.debug("Switch updated. Update {}", update.toString());
                    break;
                case SWITCH_REMOVED:
                    logger.debug("Switch removed. Update {}", update.toString());
                    break;
                case PORT_UP:
                    logger.debug("Port set up. Update {}", update.toString());
                    break;
                case PORT_DOWN:
                    logger.debug("Port set down. Update {}", update.toString());
                    break;
                default:
                    break;
            }
        }

    }

}
