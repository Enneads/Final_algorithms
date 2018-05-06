//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Final;
import java.util.*;

public class Edge {
    private Vertex source;
    private Vertex target;
    private HashSet<Vertex> edge;
    private double weight;

    /**
     * Constructor initializes an edge between the vertices source and destination with weight 1
     *
     * @param source the source of an edge
     * @param target the destination of an edge
     */
    public Edge(Vertex source, Vertex target) {
        this.source = source;
        this.target = target;
        this.edge.add(source);
        this.edge.add(target);
        this.weight = 1D;
    }

    /**
     * Overloeded constructor that initializes an edge between the vertices source and destination with specified weight
     *
     * @param source the source of an edge
     * @param target the destination of an edge
     * @param weight the weight of an edge
     */
    public Edge(Vertex source, Vertex target, double weight) {
        this.source = source;
        this.target = target;
        this.edge.add(source);
        this.edge.add(target);
        this.weight = weight;
    }

    /**
     * Overloeded constructor that initializes an edge between two vertices with names source and target with weight 1
     *
     * @param source the name of the source of an edge
     * @param target the name of the destination of an edge
     */
    public Edge(String source, String target) {
        // creates two new vertices with keys source and target
        Vertex s = new Vertex(source);
        Vertex t = new Vertex(target);

        // makes these vertices into an edge
        this.source = s;
        this.target = t;
        this.edge.add(s);
        this.edge.add(t);
        this.weight = 1D;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getTarget() {
        return target;
    }

    public Set<Vertex> getEdge() { return edge; }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getDestination() {
        return target;
    }

    public void setDestination(Vertex target) {
        this.target = target;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    /**
     * This method overrides equals. It checks if two edges are the same and returns a boolean.
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Edge other = (Edge) obj;
        if (edge.equals(obj)){
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return source.getKey() + " to " + target.getKey();
    }
}
