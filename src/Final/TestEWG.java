package Final;

import java.util.LinkedList;

public class TestEWG {

    public static void main(String[] args) {
        LinkedList<Vertex> vertices = new LinkedList<>();
        LinkedList<Edge> edges = new LinkedList<>();
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(edges, vertices);

        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        ewg.addEdge("v1", "v2");
//        ewg.addEdge("v1", "v3");
//        ewg.addEdge("v4", "v2");
//        ewg.addEdge("v1", "v2");

        System.out.println(ewg.getEdges());
        System.out.println(ewg.getVertices());
        System.out.println(ewg.toString());

    }

}
