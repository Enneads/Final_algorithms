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
    }

    public Boolean inGraph(String key){
        for (String name:names){
            if(key.equals(name)){
                return true;
        }
            return false;
    }


    public void addEdge(Vertex source, Vertex target) {
        LinkedList<Edge> edgeList = (LinkedList)this.graphMap.get(source.getKey());
        boolean found = false;
        Iterator var6 = edgeList.iterator();

        if(!names.contains(source.getKey())){
            names.add(source.getKey());
        }

        if(!names.contains(target.getKey())){
            names.add(target.getKey());
        }

        while(true) {
            Edge edge;
            do {
                if (!var6.hasNext()) {
                    if (!found) {
                        edge = new Edge(source, target);
                        edgeList.add(edge);
                    }

                    return;
                }

                edge = (Edge)var6.next();
            } while(!edge.getSource().getKey().equals(target.getKey()) && !edge.getTarget().getKey().equals(target.getKey()));

            edge.setWeight(edge.getWeight() + 1D);
            found = true;
        }
    }

    public LinkedList<String> getNames(){
        return this.names;
    }


}
