package Final;
import java.util.*;

/**
 *  The {@code Edge} class represents an undirected edge between two vertices
 *  <em>source</em> and <em>target</em>, where each edge has a real-valued weight.
 *  It supports the following primary operation: create an edge. It also provides
 *  methods for getting or setting the end vertices <em>source</em> and <em>target</em>
 *  and the weight of the edge, as well as checking if two edges are equal.
 *  <p>
 *  All operations take constant time (in the worst case).
 *
 *  @author Logan Stapleton
 *  @author Abigail Poole
 *  @author Shiyu Lin
 */
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
        edge = new HashSet<Vertex>();
        this.edge.add(source);
        this.edge.add(target);
        this.weight = 1D;
    }

    /**
     * Overloaded constructor that initializes an edge between the vertices source and destination with specified weight
     *
     * @param source the source of an edge
     * @param target the destination of an edge
     * @param weight the weight of an edge
     */
    public Edge(Vertex source, Vertex target, double weight) {
        this.source = source;
        this.target = target;
        edge = new HashSet<Vertex>();
        this.edge.add(source);
        this.edge.add(target);
        this.weight = weight;
    }

    /**
     * Overloaded constructor that initializes an edge between two vertices with names source and target with weight 1
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
        edge = new HashSet<Vertex>();
        this.edge.add(s);
        this.edge.add(t);
        this.weight = 1D;
    }

    /**
     * Returns the {@code source} Vertex (one of the ends).
     *
     * @return the {@code source} Vertex
     */
    public Vertex getSource() {
        return source;
    }

    /**
     * Returns the {@code target} Vertex (one of the ends).
     *
     * @return the {@code target} Vertex
     */
    public Vertex getTarget() {
        return target;
    }

    /**
     * Returns the {@code target} Vertex (one of the ends).
     *
     * @param s the source of the vertex you want to check
     * @return the {@code target} Vertex
     */
    public Vertex getTarget(Vertex s) {
        if(s.equals(source))
            return target;
        return source;
    }

    /**
     * Returns the {@code edge}.
     *
     * @return the {@code edge}
     */
    public Set<Vertex> getEdge() { return edge; }

    public void setSource(Vertex source) {
        this.source = source;
    }

    /**
     * Sets the target of edge {@code e}.
     *
     * @param target the target vertex
     */
    public void setTarget(Vertex target) {
        this.target = target;
    }

    /**
     * Returns the {@code target} Vertex (one of the ends).
     *
     * @return the {@code target} Vertex
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of vertex {@code v}.
     *
     * @param weight the weight of vertex {@code v}
     */
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
        return source.getKey() + " to " + target.getKey() + " with weight " + weight;
    }
}
