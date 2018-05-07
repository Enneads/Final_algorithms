package Final;

import java.util.*;

public class EdgeWeightedGraph {

    private LinkedList<Edge> edges;
    private LinkedList<Vertex> vertices;

    public EdgeWeightedGraph(LinkedList<Edge> edges,LinkedList<Vertex> vertices) {
        this.edges = edges;
        this.vertices = vertices;
    }

    public LinkedList<Edge> getEdges() {
        return this.edges;
    }

    public LinkedList<Vertex> getVertices() {
        return this.vertices;
    }

    public Vertex getVertex(String name) {
        for (Vertex v : vertices){
            if (v.getKey().equals(name)){
                return(v);
            }
        }
        return null;
    }

    public Edge getEdge(String s1, String s2) {
        for (Edge e : edges){
            if (e.contains(s1) && e.contains(s2)){
                return(e);
            }
        }
        return null;
    }

    public boolean inGraph(Vertex v){
        return(vertices.contains(v));
    }

    public boolean inGraph(String key){
        for (Vertex v:vertices){
            if(v.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean inGraph(Edge e){
        return(edges.contains(e));
    }

    public boolean areAdjacent(String s1, String s2) {
        // search edges to see if v1 and v2 are in one together
        for (Edge e : edges) {
            if (e.contains(s1) && e.contains(s2)) {
                return true;
            }
        }
        return false;
    }

    public boolean areAdjacent(Vertex v1, Vertex v2) {
        // search edges to see if v1 and v2 are in one together
        for (Edge e : edges) {
            if (e.contains(v1) && e.contains(v2)) {
                return true;
            }
        }
        return false;
    }

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

        public void addEdge(String s1, String s2){
        // 1. if neither string is in the graph
            if(!inGraph(s1) && !inGraph(s2)){
                Vertex v1 = new Vertex(s1);
                Vertex v2 = new Vertex(s2);
                Edge e = new Edge(v1,v2);

                edges.add(e);
                vertices.add(v1);
                vertices.add(v2);
            }

        // 2. if s1 is in the graph, but s2 is not
            if(inGraph(s1) && !inGraph(s2)){
                Vertex v1 = getVertex(s1);
                Vertex v2 = new Vertex(s2);

                Edge newEdge = new Edge(v1, v2); // new edge between v1  and v2 (new node)

                edges.add(newEdge);
                vertices.add(v2);
            }

        // 3. if s2 is in the graph, but s1 is not
            if(!inGraph(s1) && inGraph(s2)){
                Vertex v1 = new Vertex(s1);
                Vertex v2 = getVertex(s2);

                Edge newEdge = new Edge(v1, v2); // new edge between v1 (new node) and v2

                edges.add(newEdge);
                vertices.add(v1);
            }

        // 4. if both of the strings are in the graph...
            if(inGraph(s1) && inGraph(s2)){
            //a. ...but not connected
                if(!areAdjacent(s1,s2)){
                    Vertex v1 = getVertex(s1);
                    Vertex v2 = getVertex(s2);

                    Edge newEdge = new Edge(v1, v2); // new edge between v1 (new node) and v2

                    edges.add(newEdge);
                }

            //b. ...and connected
                else{
                    Edge e = getEdge(s1,s2);
                    double edgeWeight = e.getWeight();
                    e.setWeight(edgeWeight+1);
                }
            }
    }// end addGraph method

    public String toString(){

    }
}// end EdgeWeightedGraph class
