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


    // returns the common edge between two vertices
    // returns null if not adjacent
    public Edge findEdge(Vertex source, Vertex target){
        Edge sourceToTarget = null;

        //find the edge from source to target
        LinkedList<Edge> sourceEdges = graphMap.get(source);
        Iterator iter = sourceEdges.iterator();
        while(iter.hasNext()){
            Edge e = (Edge)iter.next();
            if(e.getTarget(source).equals(target))
                sourceToTarget = e;
        }

        return sourceToTarget;
    }

    public boolean areAdjacent(Vertex v1, Vertex v2){
        // search adjacent vertices to v1 to see if v2 is one
        for(Edge e1:graphMap.get(v1)){ // search all edges e1 connected to v1
            if(e1.getTarget(v1).equals(v2)){ // if an edge is connected to v2
                return true;
            }
        }

        // search adjacent vertices to v2 to see if v1 is one
        for(Edge e2:graphMap.get(v2)){ // search all edges e2 connected to v2
            if(e2.getTarget(v2).equals(v1)){ // if an edge is connected to v1
                return true;
            }
        }

        // if neither v1 nor v2 is in either's list of adjacent vertices,
        // then return false
        return false;
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

    // if s is in graph return false, otherwise return true
    private boolean checkVer(String s) {
        return(!names.contains(s));
    }

//    public void addEdge(String s1, String s2){
//        // 1. if neither string is in the graph
//
//        if(!inGraph(s1) && !inGraph(s2)){// checks whether a node of this
//
//        }
//
//        // 2. if one of the strings is in the graph
//
//        // 3. if both of the strings are in the graph, but not connected
//
//        // 4. if both of the strings are in the grpah and connected
//
//    }



    public void addEdge(String s1, String s2){
        //System.out.println("new vertex");
        if (s1.equals(s2)){

            //System.out.println("equals itself");
            return;
        }
        else {
            if (checkVer(s1) && checkVer(s2)){// both not in graph

                    //System.out.println("both not");
                    //When both Vertexes are not in the graph.
                    Vertex v1 = new Vertex(s1);
                    Vertex v2 = new Vertex(s2);
                    Edge newEdge = new Edge(v1,v2);
                    //System.out.println(newEdge.getWeight());
                    LinkedList<Edge> vList = new LinkedList<Edge>();
                    vList.add(newEdge);

                    graphMap.put(v1, vList);
                    graphMap.put(v2, vList);
                    names.add(s1);
                    names.add(s2);

                }// end both not in graph

            if (checkVer(s1) && !checkVer(s2)){// s2 in graph, s1 not

                    //System.out.println("s2 in graph, s1 not");
                    //s1 is not in the graph, but s2 is.
                    Vertex v1 = new Vertex(s1);
                    Vertex v2 = null;

                    // Search the set of vertices to find the
                    // vertex with key s2. Assign it to v2.
                    Set<Vertex> keys = graphMap.keySet();
                    for (Vertex v : keys) {
                        if (v.getKey().equals(s2)) {
                            v2 = v;
                        }
                    }
                    Edge newEdge = new Edge(v1, v2); // new edge between v1 (new node) and v2
                    //System.out.println(newEdge.getWeight());

                    // Add new edge to v1's list of edges
                    LinkedList<Edge> v1List = new LinkedList<Edge>();
                    v1List.add(newEdge);
                    graphMap.put(v1, v1List);
                    names.add(s1);

                    // Add new edge to v2's list of edges
                    LinkedList<Edge> v2List = graphMap.get(v2);
                    v2List.add(newEdge);

                }// end s2 in graph, s1 not

            if (!checkVer(s1) && checkVer(s2)) {// s1 in graph, s2 not

                //System.out.println("s1 in graph, s2 not");
                if (checkVer(s2)) { //if s2 is not in graph

                    //make new vertex v2 with key s2
                    Vertex v2 = new Vertex(s2);

                    // search vertices for vertex with key s1,
                    // set it to v1
                    Vertex v1 = null;
                    Set<Vertex> keys = graphMap.keySet();
                    for (Vertex v : keys) {
                        if (v.getKey().equals(s1)) {
                            v1 = v;
                        }
                    }

                    // make new edge
                    Edge newEdge = new Edge(v1, v2);
                    //System.out.println(newEdge.getWeight());

                    // set edge to v2 (not previously in) and add to graph
                    LinkedList<Edge> v2List = new LinkedList<Edge>();
                    v2List.add(newEdge);
                    graphMap.put(v2, v2List);
                    names.add(s2);

                    // add edge to v1 (already in graph)
                    LinkedList<Edge> foundVer = graphMap.get(v1);
                    foundVer.add(newEdge);
                }

            }// end s1 in graph, s2 not

            if (!checkVer(s1) && !checkVer(s2)) {// s1 and s2 in graph

                    //System.out.println("s1 and s2 in graph...");
                    Vertex v1 = null;
                    Vertex v2 = null;

                    // search vertices for one with key that matches s1 and
                    // one with key that matches s2
                    Set<Vertex> keys = graphMap.keySet();
                    for (Vertex v : keys) {
                        if (v.getKey().equals(s1)) { // v1 matches s1
                            v1 = v;
                        }
                        if (v.getKey().equals(s2)) { // v2 matches s2
                            v2 = v;
                        }
                    }

                    Edge edge12 = findEdge(v1, v2); // find shared edge between v1 and v2

                    // if v1 and v2 are not adjacent
                    if (edge12==null){

                        //System.out.println("... but not adjacent");

                        // add an edge between v1 and v2
                        Edge newEdge = new Edge(v1,v2);

                        LinkedList<Edge> vList1 = graphMap.get(v1);
                        LinkedList<Edge> vList2 = graphMap.get(v2);
                        vList1.add(newEdge);
                        vList2.add(newEdge);
                    }

                    // if v1 and v2 are adjacent
                    // increase the weight of their shared edge by 1
                    else {

                        //System.out.println("... and adjacent");
                        double edgeWeight = edge12.getWeight();
                        edgeWeight=edgeWeight+1;
                        edge12.setWeight(edgeWeight);
                    }
                }
            }

        }



    public LinkedList<String> getNames(){
        return this.names;
    }

//    @Override
//    public String toString() {
//        // toString to an adjacency list
//        String str = "";
//        Set<Vertex> keys = graphMap.keySet();
//        for (Vertex v : keys) {
//            str = str.concat("\n" + v.getKey() + "(" + v.getWeight() + ")"); // separate lines for every new node
//            LinkedList<Edge> edgelist = graphMap.get(v);
//            Iterator iter = edgelist.iterator();
//            while (iter.hasNext()) {
//                Edge e = (Edge) iter.next();
//                Vertex end = e.getTarget(v);
//                str = str.concat(" -" + e.getWeight() + "-> " + end.getKey() + "(" + end.getWeight() + ")");
//            }
//        }
//        return str;
//    }

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
