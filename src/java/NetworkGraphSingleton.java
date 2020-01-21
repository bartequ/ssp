package pl.edu.agh.kt;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class NetworkGraphSingleton {

	private static Graph<String, DefaultEdge> graph;
    
	private static NetworkGraphSingleton instance;
	
	private NetworkGraphSingleton() {};
	
	public static Graph<String, DefaultEdge> getInstance() {
		if(graph == null) {
			graph =  new DefaultDirectedGraph<>(DefaultEdge.class);
		}
		return graph;
	}
	
}
