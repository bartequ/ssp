package pl.edu.agh.kt;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class NetworkGraphSingleton {

	private static Graph graph;
    
	private static NetworkGraphSingleton instance;
	
	private NetworkGraphSingleton() {
		
	};
	
	public static Graph getInstance() {
		if(instance == null) {
			return new DefaultDirectedGraph<>(DefaultEdge.class);
		} else {
			return graph;
		}
	}
	
}
