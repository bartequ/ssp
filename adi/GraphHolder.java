package pl.edu.agh.kt;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;

public class GraphHolder {

    private static DefaultDirectedWeightedGraph<String, DefaultEdge> instance;

    private GraphHolder(){}

    public static synchronized DefaultDirectedWeightedGraph<String, DefaultEdge> getInstance(){
        if(instance == null){
            instance = new DefaultDirectedWeightedGraph<>(DefaultEdge.class);
        }
        return instance;
    }

}
