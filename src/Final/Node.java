//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Final;

import java.util.LinkedList;
import java.util.Iterator;

public class Node {
    private String key;
    private double weight;
    private LinkedList<Edge> adj;

    /**
     * Initializes a Node with label (key), weight, and list of adjacent edges.
     *
     * @param key
     * @param weight
     * @param adj
     */
    public Node(String key, double weight, LinkedList<Edge> adj) {
        this.key = key;
        this.weight = weight;
        this.adj = adj;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public LinkedList<Edge> getEdges() {
        return this.adj;
    }

    public void setEdges(LinkedList<Edge> edges) {
        this.adj = edges;
    }

    public void addEdges(LinkedList<Edge> edges){
        Iterator<Edge> iter1 = adj.iterator();
        Iterator<Edge> iter2 = edges.iterator();


    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

// textfiles into line-by-line strings
// line-by-line strings : filter trivial words
// line-by-line strings : call the function
