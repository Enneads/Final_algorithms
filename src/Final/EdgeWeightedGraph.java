package Final;

import java.util.*;


/**
 *  The {@code EdgeWeightedGraph} class represents an undirected, edge-weighted 
 *  graph where each edge and vertex each has a real-valued weight.
 *  It supports the following primary operation: add an edge. It also provides
 *  methods for checking whether edges and vertices are in the graph, whether 
 *  they are adjacent, getting the vertex with a given name, and getting the edge
 *  between vertices.
 *  <p>
 *
 *  @author Logan Stapleton
 *  @author Abigail Poole
 *  @author Shiyu Lin
 */
public class EdgeWeightedGraph {

    private LinkedList<Edge> edges;
    private LinkedList<Vertex> vertices;

    /**
     * Constructor creates an undirected, weighted graph stored as lists of edges and vertices.
     *
     * @param edges list of edges
     * @param vertices list of vertices
     */
    public EdgeWeightedGraph(LinkedList<Edge> edges,LinkedList<Vertex> vertices) {
        this.edges = edges;
        this.vertices = vertices;
    }

    /**
     * Overloaded constructor initializes graph with empty lists of vertices and edges.
     *
     */
    public EdgeWeightedGraph(){
        this.edges = new LinkedList<Edge>();
        this.vertices = new LinkedList<Vertex>();
    }

    /**
     * Overloaded constructor creates a deep copy of a weighted graph.
     *
     * @param G edge-weighted graph to be copied
     */
    public EdgeWeightedGraph(EdgeWeightedGraph G) {
        LinkedList<Edge> edgelist = new LinkedList<Edge>();
        LinkedList<Vertex> vertexlist = new LinkedList<Vertex>();

        for(Edge e : G.getEdges()){
            Edge newEdge = new Edge(e.getSource(),e.getTarget());
        }

        this.edges = G.getEdges();
        this.vertices = G.getVertices();
    }
    
    /**
     * Returns a list of edges in the graph
     *
     * @return the {@ code edges} in graph
     */
    public LinkedList<Edge> getEdges() {
        return this.edges;
    }

    /**
     * Returns a list of vertices in the graph
     *
     * @return the {@code vertices} in graph
     */
    public LinkedList<Vertex> getVertices() {
        return this.vertices;
    }
    
    /**
     * Returns a vertex with the key {@code name}.
     *
     * @return the vertex with key {@code name} in graph,
     * @return null if no vertex with key {@code name} in graph.
     */
    public Vertex getVertex(String name) {
        for (Vertex v : vertices){
            if (v.getKey().equals(name)){
                return(v);
            }
        }
        return null;
    }

    /**
     * Returns the {@code edge} with two input strings at end.
     *
     * @param s1 a string of the {@code key} of one end of the target edge
     * @param s2 a string of the {@code key} of the other end of the target edge
     * @return the edge with end vertices with keys {@code s1} and {@code s2}
     * @return null if no such edge with ends {@code s1} and {@code s2} in graph.
     */
    public Edge getEdge(String s1, String s2) {
        for (Edge e : edges){
            if (e.contains(s1) && e.contains(s2)){
                return(e);
            }
        }
        return null;
    }

    /**
     * Checks if a vertex {@code v} is in the graph.
     *
     * @return true if vertex {@code v} in graph, false otherwise
     */
    public boolean inGraph(Vertex v){
        return(vertices.contains(v));
    }

    /**
     * Checks if a vertex with the key {@code key} in the graph.
     *
     * @param key the name of the vertex to search for
     * @return true if vertex with key {@code key} in graph, false otherwise
     */
    public boolean inGraph(String key){
        for (Vertex v:vertices){
            if(v.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if an edge {@code e} is in the graph.
     *
     * @param e the edge to search for
     * @return true if edge {@code e} in graph, false otherwise
     */
    public boolean inGraph(Edge e){
        return(edges.contains(e));
    }

    /**
     * Checks if two words are adjacent in text graph.
     *
     * @param s1 the name of the vertex to check if adjacent to s2
     * @param s2 the name of the vertex to check if adjacent to s1
     * @return true if vertices with keys {@code s1} and {@code s2} share an edge, false otherwise
     */
    public boolean areAdjacent(String s1, String s2) {
        // search edges to see if v1 and v2 are in one together
        for (Edge e : edges) {
            if (e.contains(s1) && e.contains(s2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if two vertices are adjacent in text graph.
     *
     * @param v1 the vertex to check if adjacent to v2
     * @param v2 the vertex to check if adjacent to v1
     * @return true if vertices {@code v1} and {@code v2} share an edge, false otherwise
     */
    public boolean areAdjacent(Vertex v1, Vertex v2) {
        // search edges to see if v1 and v2 are in one together
        for (Edge e : edges) {
            if (e.contains(v1) && e.contains(v2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all edges that are adjacent to a given vertex {@code v}.
     *
     * @param v the vertex to find adjacent edges to
     * @return adj a linked list of edges that contain vertex {@code v}
     */
    public LinkedList<Edge> adjacentTo(Vertex v){
        LinkedList<Edge> adj = new LinkedList();

        // Searches edges to see if v1 and v2 are in one together,
        // then returns that edge.
        // Returns null if v is not adjacent to any other vertices.
        for (Edge e : edges) {
            if (e.contains(v)) {
                adj.add(e);
            }
        }
        return adj;
    }

    /**
     * Returns the {@code edge} with two input vertices for ends.
     *
     * @param v1 one end of the target edge
     * @param v2 the other end of the target edge
     * @return the edge with end vertices {@code v1} and {@code v2}
     * @return null if no such edge with vertices {@code v1} and {@code v2} in graph.
     */
    public Edge findEdge(Vertex v1, Vertex v2){
        Edge adj = null;

        // Searches edges to see if v1 and v2 are in one together,
        // then returns that edge.
        // Returns null if edges are not adjacent.
        for (Edge e : edges) {
            if (e.contains(v1) && e.contains(v2)) {
                adj = e;
            }
        }
        return adj;
    }

    /**
     * Adds an {@code edge} with two input strings to the text graph.
     * Four possibilities:
     *  1. both vertices are not in the graph, add vertices and edge between
     *  2. only one vertex is the in the graph, add the other vertex and edge between
     *  3. both vertices are in the graph but not adjacent, add an edge between
     *  4. both vertices are in the graph and adjacent, increase weight of adjacent edge by 1
     *
     * @param s1 a string of the {@code key} of one end of the target edge
     * @param s2 a string of the {@code key} of the other end of the target edge
     */
        public void addEdge(String s1, String s2){
        // 1. if neither string is in the graph
            if(!inGraph(s1) && !inGraph(s2)){
                Vertex v1 = new Vertex(s1);
                Vertex v2 = new Vertex(s2);
                Edge e = new Edge(v1,v2);
//                System.out.println(e.getWeight());

                edges.add(e);
                vertices.add(v1);
//                System.out.println(e.getWeight());
                vertices.add(v2);
                return;
            }//end if statement

        // 2. if s1 is in the graph, but s2 is not
            if(inGraph(s1) && !inGraph(s2)){
                Vertex v1 = getVertex(s1);
                Vertex v2 = new Vertex(s2);

                Edge newEdge = new Edge(v1, v2); // new edge between v1  and v2 (new node)

                edges.add(newEdge);
                vertices.add(v2);
                return;
            }//end if statement

        // 3. if s2 is in the graph, but s1 is not
            if(!inGraph(s1) && inGraph(s2)){
                Vertex v1 = new Vertex(s1);
                Vertex v2 = getVertex(s2);

                Edge newEdge = new Edge(v1, v2); // new edge between v1 (new node) and v2

                edges.add(newEdge);
                vertices.add(v1);
                return;
            }//end if statement

        // 4. if both of the strings are in the graph...
            if(inGraph(s1) && inGraph(s2)){
            //a. ...but not connected
                if(!areAdjacent(s1,s2)){
                    Vertex v1 = getVertex(s1);
                    Vertex v2 = getVertex(s2);

                    Edge newEdge = new Edge(v1, v2); // new edge between v1 (new node) and v2

                    edges.add(newEdge);
                }//end if statement

            //b. ...and connected
                else if (areAdjacent(s1,s2)) {
                    Edge e = getEdge(s1,s2);
                    double edgeWeight = e.getWeight();
                    e.setWeight(edgeWeight+1);
                }//end else statement
            }//end if statement
    }// end addEdge method

    /**
     * Creates a deep copy of this edge-weighted graph
     *
     * @return clone of edge-weighted graph
     */
    @Override
    public EdgeWeightedGraph clone(){
        EdgeWeightedGraph newGraph = new EdgeWeightedGraph();
        LinkedList<Edge> edgelist = this.getEdges();
        LinkedList<Vertex> vertexlist = this.getVertices();

        for (Edge e : edgelist){
            for(int i = 0; i < e.getWeight(); i++) {
                String s1 = e.getSource().getKey();
                String s2 = e.getTarget().getKey();
                newGraph.addEdge(s1, s2);
            }
        }

        for (Vertex v : vertexlist){
            newGraph.getVertex(v.getKey()).setWeight(v.getWeight());
        }

        return newGraph;
    }

    @Override
    /**
     * Returns a string that makes the graph into an adjacency list.
     *
     * @return str the string of the adjacency list of this graph
     */
    public String toString() {

        Map<Vertex, LinkedList<Edge>> graphMap = new HashMap<Vertex, LinkedList<Edge>>();

        for (Vertex v : vertices) {
            graphMap.put(v, adjacentTo(v));
        }

        // toString to an adjacency list
        String str = "";
        Set<Vertex> keys = graphMap.keySet();
        for (Vertex v : keys) {
            str = str.concat("\n" + v.getKey() + "(" + v.getWeight() + ")"); // separate lines for every new node
            LinkedList<Edge> edgelist = graphMap.get(v);
            Iterator iter = edgelist.iterator();
            while (iter.hasNext()) {
                Edge e = (Edge) iter.next();
                Vertex end = e.getTarget(v);
                str = str.concat(" -" + e.getWeight() + "-> " + end.getKey() + "(" + end.getWeight() + ")");
            }
        }
        return str;
    }



}// end EdgeWeightedGraph class
