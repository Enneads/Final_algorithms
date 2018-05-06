//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Final;

public class Edge {
    private Vertex source;
    private Vertex target;
    private double weight;

    /**
     * Initializes an edge between the vertices source and destination
     *
     * @param source the source of an edge
     * @param target the destination of an edge
     */
    public Edge(Vertex source, Vertex target) {
        this.source = source;
        this.target = target;
        this.weight = 1D;
    }

    /**
     * Initializes an edge between the vertices source and destination
     *
     * @param source the source of an edge
     * @param target the destination of an edge
     * @param weight the weight of an edge
     */
    public Edge(Vertex source, Vertex target, double weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getTarget() {
        return target;
    }

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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Edge other = (Edge) obj;
        if ((this.source.equals(other.source) && this.target.equals(other.target)) || (this.source.equals(other.target) && this.target.equals(other.source))){
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return source.getKey() + " to " + target.getKey();
    }
}
