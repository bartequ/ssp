package pl.edu.agh.kt;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import net.floodlightcontroller.linkdiscovery.ILinkDiscovery;
import net.floodlightcontroller.linkdiscovery.ILinkDiscovery.LDUpdate;

public class Topology {
	public Topology() {}

	GraphPath<String, DefaultEdge> findShortestPath(ILinkDiscovery.LDUpdate update) {
	Graph<String, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);
	String v1 = "v1";
	String v2 = "v2";
	String v3 = "v3";
	String v4 = "v4";
	// add the vertices
	g.addVertex(v1);
	g.addVertex(v2);
	g.addVertex(v3);
	g.addVertex(v4);
	// add edges
	g.addEdge(v1,v2);
	g.addEdge(v2,v3);
	g.addEdge(v3,v4);
	g.addEdge(v4,v1);
	
	GraphPath<String, DefaultEdge> sp = DijkstraShortestPath.findPathBetween(g, "v1", "v2");
	return sp;
	}
}
