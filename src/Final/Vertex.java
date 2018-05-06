package Final;

/**
 *  The {@code Vertex} class represents a vertex with a <em>name</em> and a <em>weight</em>.
 *  It supports the following primary operation: create a vertex. It also provides
 *  methods for getting or setting the <em>key</em> and <em>weight</em>,
 *  as well as checking if a vertex is equal to another object.
 *  <p>
 *  All operations take constant time (in the worst case).
 *
 *  @author Logan Stapleton
 *  @author Abigail Poole
 *  @author Shiyu Lin
 */
public class Vertex {
    private String key;
    private double weight;

    /**
     * Constructor initializes a vertex with name key and specified weight.
     *
     * @param key
     * @param weight
     */
    public Vertex(String key, double weight) {
        this.key = key;
        this.weight = weight;
    }

    /**
     * Overloaded constructor initializes a vertex with name key and weight 1.
     *
     * @param key
     */
    public Vertex(String key) {
        this.key = key;
        this.weight = 0D;
    }

    /**
     * Returns the name of vertex {@code v}.
     *
     * @return the name of vertex {@code v}
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the weight of vertex {@code v}.
     *
     * @return the weight of vertex {@code v}
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of vertex {@code v}.
     *
     * @param wt the weight of vertex {@code v}
     */
    public void setWeight(double wt) {
        weight = wt;
    }

    @Override
    /**
     * This method overrides equals. It checks if two vertices are the same and returns a boolean.
     *
     * @param obj the object to check against {@code v}
     * @returns true if {@code v} equals {@code obj}; false if not
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key)){
            return false;
        }
        return true;
    }

    @Override
    /**
     * Returns a string representation of the vertex.
     *
     * @return the name of the vertex and the weight
     */
    public String toString() {
        return key + " with node-weight " + weight;
    }
}
