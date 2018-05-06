package Final;

import java.util.LinkedList;

public class EdgeWeightGraph {//start class

    private final int V;
    private int E;
    private LinkedList<Edge>[] adj;

    /**
     * Constructor for EdgeWeiightGraph
     *
     * Initializes an empty edge-weighted graph with V vertices and 0 edges.
     *
     * @param V the number of vertices
     * @throws IllegalArgumentException if V < 0
     */
    public EdgeWeightGraph(int V){//start constructor 1
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (LinkedList<Edge>[]) new LinkedList[V];
        for (int v = 0; v < V; v++){//start loop
            adj[v] = new LinkedList<Edge>();
        }//end loop
    }//end constructor 1

    /**
     * Initializes a random edge-weighted graph from an input.
     * The format is the number of Vertices,
     * followed by the number of edges,
     * followed by E pairs of vertices and edge weights,
     * with each entry separated by whitespace.
     *
     * @param N an array of nodes
     * @param E an array of edges between these nodes
     * @IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @IllegalArgumentException if the number of vertices or edges is negative
     */
    public EdgeWeightGraph(Node[] N, Edge[] E){//start constructor 2

    }//end constructor 2



}//end class
