//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Final;

public class Edge {
    private Node vA;
    private Node vB;
    private double weight;

    public Edge(Node vA, Node vB) {
        this.vA = vA;
        this.vB = vB;
        this.weight = 1.0D;
    }

    public Node getvA() {
        return this.vA;
    }

    public void setvA(Node vA) {
        this.vA = vA;
    }

    public Node getvB() {
        return this.vB;
    }

    public void setvB(Node vB) {
        this.vB = vB;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
