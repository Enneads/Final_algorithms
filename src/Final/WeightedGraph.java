//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Final;

import java.io.IOException;
import java.util.*;

public class WeightedGraph {
    private Map<Vertex, LinkedList<Edge>> graphMap;
    private LinkedList<String> names;
//    private LinkedList<Edge> edges;

    public WeightedGraph(Map<Vertex, LinkedList<Edge>> graphMap) {
        this.graphMap = graphMap;

        // create a list of names of vertices
        this.names = new LinkedList<String>();
        Set<Vertex> vertices = graphMap.keySet();
        for (Vertex v:vertices) {
            this.names.add(v.getKey());
        }
    }

    public Map<Vertex, LinkedList<Edge>> getGraphMap() {
        return this.graphMap;
    }

    public boolean inGraph(Vertex v){
        if (graphMap.containsKey(v)){
            return true;
        }
        return false;
    }

    public boolean inGraph(String key){
        for (String name:names){
            if(key.equals(name)) {
                return true;
            }
        }
            return false;
    }

    public boolean inGraph(Edge e){
            return(graphMap.containsValue(e));
    }


    public void addEdge(Edge e){
        // makes source vertex if none exists
        if (!inGraph(e.getSource().getKey()) || !inGraph(e.getTarget().getKey())) {
            if (!inGraph(e.getSource().getKey())) {
                names.add(e.getSource().getKey());
                LinkedList<Edge> edges = new LinkedList<>();
                edges.add(e);
                graphMap.put(e.getSource(), edges);
//            graphMap.get(e.getSource()).add(e);
            }

            // makes target vertex if none exists
            if (!inGraph(e.getTarget().getKey())) {
                names.add(e.getTarget().getKey());
                LinkedList<Edge> edges = new LinkedList<>();
                edges.add(e);
                graphMap.put(e.getTarget(), edges);
//            graphMap.get(e.getTarget()).add(e);
            }
        }

        // increments edge weight by 1 if edge exists
        else {
            if (inGraph(e)) {
                LinkedList<Edge> sEdges = graphMap.get(e.getSource());
                int i = sEdges.indexOf(e);
                sEdges.get(i).setWeight(sEdges.get(i).getWeight() + 1);
                graphMap.remove(e.getSource());
                graphMap.put(e.getSource(), sEdges);

                LinkedList<Edge> tEdges = graphMap.get(e.getTarget());
                int j = tEdges.indexOf(e);
                tEdges.get(i).setWeight(sEdges.get(i).getWeight() + 1);
                graphMap.remove(e.getTarget());
                graphMap.put(e.getTarget(), tEdges);
            }
        }



    }


    private boolean checkVer(String s) {
        if (names.contains(s)) {
            return false;
        }
        else {
            return true;
        }

    }


    public void addEdge(String s1, String s2){
        System.out.println(s1);
        System.out.println(s2);
        if (s1.equals(s2)){
            return;
        }
        else {
            if (checkVer(s1)){
                if (checkVer(s2)){
                    //When both Vertexes are not in the graph.
                    Vertex v1 = new Vertex(s1);
                    Vertex v2 = new Vertex(s2);
                    Edge newEdge = new Edge(v1,v2);
                    System.out.println(newEdge.getWeight());
                    LinkedList<Edge> vList = new LinkedList<Edge>();
                    vList.add(newEdge);

                    graphMap.put(v1, vList);
                    graphMap.put(v2, vList);
                    names.add(s1);
                    names.add(s2);

                }
                else {
                    //s1 is not in the graph. s2 is.
                    Vertex v1 = new Vertex(s1);
                    Vertex v2 = null;
                    Set<Vertex> keys = graphMap.keySet();
                    for (Vertex v : keys) {
                        if (v.getKey().equals(s2)) {
                            v2 = v;
                        }
                    }
                    Edge newEdge = new Edge(v1, v2);
                    System.out.println(newEdge.getWeight());
                    LinkedList<Edge> vList = new LinkedList<Edge>();
                    vList.add(newEdge);
                    graphMap.put(v1, vList);
                    names.add(s1);
                    LinkedList<Edge> foundVer = graphMap.get(v2);
                    foundVer.add(newEdge);
                    graphMap.remove(v2);
                    graphMap.put(v2,foundVer);

//                    Edge newEdge = new Edge(s1,s2);
//                    LinkedList<Edge> vList = new LinkedList<Edge>();
//                    vList.add(newEdge);
//                    Vertex v1 = new Vertex(s1);
//                    graphMap.put(v1, vList);
//                    names.add(s1);
//                    Vertex ver = null;
//                    Set<Vertex> keys = graphMap.keySet();
//                    for (Vertex v : keys) {
//                        if (v.getKey().equals(s2)) {
//                            ver = v;
//                        }
//                    }
//                    LinkedList<Edge> foundVer = graphMap.get(ver);
//                    foundVer.add(newEdge);
//                    graphMap.remove(ver);
//                    graphMap.put(ver,foundVer);
                }
            }
            else {
                if (checkVer(s2)) {
                    Vertex v2 = new Vertex(s2);
                    Vertex v1 = null;
                    Set<Vertex> keys = graphMap.keySet();
                    for (Vertex v : keys) {
                        if (v.getKey().equals(s1)) {
                            v1 = v;
                        }
                    }
                    Edge newEdge = new Edge(v1, v2);
                    System.out.println(newEdge.getWeight());

                    LinkedList<Edge> vList = new LinkedList<Edge>();
                    vList.add(newEdge);
                    graphMap.put(v2, vList);
                    names.add(s2);
                    LinkedList<Edge> foundVer = graphMap.get(v1);
                    foundVer.add(newEdge);
                    graphMap.remove(v1);
                    graphMap.put(v1,foundVer);

                    //s1 is in the graph. s2 is not.
//                    Edge newEdge = new Edge(s1, s2);
//                    LinkedList<Edge> vList = new LinkedList<Edge>();
//                    vList.add(newEdge);
//                    Vertex v2 = new Vertex(s2);
//                    graphMap.put(v2, vList);
//                    names.add(s2);
//                    Vertex ver = null;
//                    Set<Vertex> keys = graphMap.keySet();
//                    for (Vertex v : keys) {
//                        if (v.getKey().equals(s1)) {
//                            ver = v;
//                        }
//                    }
//                    LinkedList<Edge> foundVer = graphMap.get(ver);
//                    foundVer.add(newEdge);
//                    graphMap.remove(ver);
//                    graphMap.put(ver, foundVer);
                }

                else {
                    // s1 is in the graph. s2 is in the graph.
                    Vertex ver1 = null;
                    Vertex ver2 = null;
                    Set<Vertex> keys = graphMap.keySet();
                    for (Vertex v : keys) {
                        if (v.getKey().equals(s1)) {
                            ver1 = v;
                        }
                        if (v.getKey().equals(s2)) {
                            ver2 = v;
                        }
                    }
                    LinkedList<Edge> foundVer1 = graphMap.get(ver1);
                    LinkedList<Edge> foundVer2 = graphMap.get(ver2);
                    Iterator iter = foundVer1.iterator();
                    while (iter.hasNext()) {
                        Edge verEdge1 = (Edge) iter.next();
                        if (verEdge1.getTarget().equals(ver2) || verEdge1.getSource().equals(ver2)) {
                            verEdge1.setWeight(verEdge1.getWeight() + 1);
                            System.out.println(verEdge1.getWeight());

                        }

//                    foundVer.add(newEdge);
//                    graphMap.remove(ver);
//                    graphMap.put(ver,foundVer);
                    }
                    Iterator it = foundVer2.iterator();
                    while (it.hasNext()) {
                        Edge verEdge2 = (Edge) it.next();
                        if (verEdge2.getSource().equals(ver1) || verEdge2.getTarget().equals(ver1)) {
                            verEdge2.setWeight(verEdge2.getWeight() + 1);
                            System.out.println(verEdge2.getWeight());

                        }
                    }
                }
            }

        }
    }


//        if (!this.inGraph(e)){ // edge is not in graph
//            // if neither ends are in the graph
//            if ()
//            // if one or both of the ends are not in the graph
//        }

//
//        else {
//            // increase edge weight by 1
//        }
//
//
//
//        // if ends are in the graph, but there is no edge
//
//        // if ends are in the graph and there is an edge between them
//
//        // if edge is already in edge list
//        // this assumes v1 and v2 are already in the graph
//        Vertex v1 = e.getSource();
//        Vertex v2 = e.getTarget();
//        LinkedList<Edge> edgelist = (LinkedList)this.graphMap.






//    public void addEdge(Vertex source, Vertex target) {
//        // if neither source nor target in graph
//        //if(!this.inGraph(source) && !this.inGraph(target)) throw new IllegalArgumentException;
//
//        LinkedList<Edge> edgeList = (LinkedList)this.graphMap.get(source);
//        boolean found = false;
//        Iterator iter = edgeList.iterator();
//
//        // if no source in graph
//        if(!names.contains(source.getKey())){
//            names.add(source.getKey());
//        }
//
//        // if no target in graph
//        if(!names.contains(target.getKey())){
//            names.add(target.getKey());
//        }
//
//
//        while(true) {
//            Edge edge;
//            do {
//                if (!iter.hasNext()) {
//                    if (!found) {
//                        edge = new Edge(source, target);
//                        edgeList.add(edge);
//                    }
//
//                    return;
//                }
//
//                edge = (Edge)iter.next();
//            } while(!edge.getSource().equals(target) && !edge.getTarget().equals(target));
//
//            edge.setWeight(edge.getWeight() + 1D);
//            found = true;
//        }
//    }

//    public void addEdge(String source, String target) {
//        // if neither source nor target in graph
//        //if(!this.inGraph(source) && !this.inGraph(target)) throw new IllegalArgumentException;
//
//        LinkedList<Edge> edgeList = (LinkedList)this.graphMap.get(source);
//        boolean found = false;
//        Iterator iter = edgeList.iterator();
//
//        // if no source in graph
//        if(!names.contains(source)){
//            names.add(source); // NEED TO ADD METHOD TO ACTUALLY ADD THIS VERTEX TO THE GRAPH
//        }
//
//        // if no target in graph
//        if(!names.contains(target)){
//            names.add(target); // NEED TO ADD METHOD TO ACTUALLY ADD THIS VERTEX TO THE GRAPH
//        }
//
//        while(true) {
//            Edge edge;
//            do {
//                if (!iter.hasNext()) {
//                    if (!found) {
//                        edge = new Edge(source, target);
//                        edgeList.add(edge);
//                    }
//
//                    return;
//                }
//
//                edge = (Edge)iter.next();
//            } while(!edge.getSource().equals(target) && !edge.getTarget().equals(target));
//
//            edge.setWeight(edge.getWeight() + 1D);
//            found = true;
//        }
//    }


    public LinkedList<String> getNames(){
        return this.names;
    }

    @Override
    public String toString() {
        String str = "WeightedGraph{";
        Set<Vertex> keys = graphMap.keySet();
        for (Vertex v : keys) {
            str = str.concat("\nNAME: " + v.getKey() + " connected to:");
            LinkedList<Edge> edges = graphMap.get(v);
            for (Edge e : edges) {
                str = str.concat(e.getTarget().toString() + " (" + e.getWeight() + "), ");
            }
        }
        return str + '}';
    }
}
