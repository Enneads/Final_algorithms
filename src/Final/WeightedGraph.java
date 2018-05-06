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

    public WeightedGraph(Map<Vertex, LinkedList<Edge>> graphMap) {
        this.graphMap = graphMap;

        this.names = new LinkedList<String>();
        Set<Vertex> vertices = graphMap.keySet();
        for (Vertex v:vertices) {
            this.names.add(v.getKey());
        }
    }

    public Map<Vertex, LinkedList<Edge>> getGraphMap() {
        return this.graphMap;
    }

    public Boolean inGraph(Vertex v){
        if (graphMap.containsKey(v)){
            return true;
        }
        return false;
    }

    public Boolean inGraph(String key){
        for (String name:names){
            if(key.equals(name)) {
                return true;
            }
        }
            return false;
    }


    public void addEdge(Vertex source, Vertex target) {
        // if neither source nor target in graph
        //if(!this.inGraph(source) && !this.inGraph(target)) throw new IllegalArgumentException;
        
        LinkedList<Edge> edgeList = (LinkedList)this.graphMap.get(source);
        boolean found = false;
        Iterator iter = edgeList.iterator();
           
        // if no source in graph
        if(!names.contains(source.getKey())){
            names.add(source.getKey());
        }
        
        // if no target in graph
        if(!names.contains(target.getKey())){
            names.add(target.getKey());
        }

        
        while(true) {
            Edge edge;
            do {
                if (!iter.hasNext()) {
                    if (!found) {
                        edge = new Edge(source, target);
                        edgeList.add(edge);
                    }

                    return;
                }

                edge = (Edge)iter.next();
            } while(!edge.getSource().equals(target) && !edge.getTarget().equals(target));

            edge.setWeight(edge.getWeight() + 1D);
            found = true;
        }
    }

    public void addEdge(String source, String target) {
        // if neither source nor target in graph
        //if(!this.inGraph(source) && !this.inGraph(target)) throw new IllegalArgumentException;

        LinkedList<Edge> edgeList = (LinkedList)this.graphMap.get(source);
        boolean found = false;
        Iterator iter = edgeList.iterator();

        // if no source in graph
        if(!names.contains(source)){
            names.add(source); // NEED TO ADD METHOD TO ACTUALLY ADD THIS VERTEX TO THE GRAPH
        }

        // if no target in graph
        if(!names.contains(target)){
            names.add(target); // NEED TO ADD METHOD TO ACTUALLY ADD THIS VERTEX TO THE GRAPH
        }


        while(true) {
            Edge edge;
            do {
                if (!iter.hasNext()) {
                    if (!found) {
                        edge = new Edge(source, target);
                        edgeList.add(edge);
                    }

                    return;
                }

                edge = (Edge)iter.next();
            } while(!edge.getSource().equals(target) && !edge.getTarget().equals(target));

            edge.setWeight(edge.getWeight() + 1D);
            found = true;
        }
    }


    public LinkedList<String> getNames(){
        return this.names;
    }


}
