//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Final;

import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class TextNetwork {
    private Map map = new HashMap();
    WeightedGraph graph;

    /**
     * This class reads the text by line and makes a graph by creating an edge between two words in the same line.
     */

    public TextNetwork() {
        this.graph = new WeightedGraph(new HashMap<Vertex, LinkedList<Edge>>());
    }

    public TextNetwork(WeightedGraph graph) {
        this.graph = graph;
    }


    public WeightedGraph getGraph() {
        return graph;
    }

    public void setGraph(WeightedGraph graph) {
        this.graph = graph;
    }public void makeGraph(String fileName) throws IOException {
//        Map<Vertex, LinkedList<Edge>> mapp = new HashMap<>();
//        WeightedGraph wGraph = new WeightedGraph(mapp);
        ReadFile file = new ReadFile(fileName);
        String[] text = file.OpenFile();

        // parses through every line in text
        for (String line : text) {
            // make line uppercase
            line = line.toUpperCase();

            // an array of words in a line
            String[] wordList = cutString(line);

            LinkedList<String> inLine = new LinkedList<String>();

            // parse through every word in line
            for (String i : wordList) {
                if(!inLine.contains(i)){
                    inLine.add(i);
                }
                for (String j : inLine) {
                    //Edge e = new Edge(i, j);
                    graph.addEdge(i, j);
                }
            }
        }
    }

    /**
     * This method cuts a line into words.
     * @param line
     * @return
     */
    private String[] cutString(String line){

//        String[] cuttedLine = line.split("\\W+");
        return line.split("\\W+");
    }

    public void SpreadingActivation(int iterTime, LinkedList<String> active) {
        Map<Vertex, LinkedList<Edge>> network = graph.getGraphMap();

        // Create an initial network where important vertices (those in active list)
        // are assigned weight 1; every other vertex has weight 0.
        for (Vertex v : network.keySet()) { // loops over every vertex in network
            Iterator iter = active.iterator();
            while (iter.hasNext()) { // loops over every word in active
                String word = (String) iter.next();
                if (v.getKey().equals(word)) { // checks if vertex key is in active
                    v.setWeight(1D);
                }
            }
        }


        // loops through iterTime number of iterations
        for (int i=0; i<iterTime; i++){

            // single iteration
            for (Vertex v : network.keySet()) {
                int degree = 0;
                double sum = 0;

                LinkedList<Edge> edgelist = network.get(v);

                Iterator iter2 = edgelist.iterator();
                while (iter2.hasNext()){
                    Edge e = (Edge)iter2.next();
                    double edgeweight = e.getWeight();
                    Vertex u = e.getSource();
//                    for (Vertex v2:e.getEdge()){
//                        if (!v2.equals(v)){
//                            u = v2;
//                        }
//                    }

                    double nodeweight = u.getWeight();
                    sum += nodeweight*Math.exp(-1/edgeweight);
                    degree++;

                    if(v.getWeight() != 1D){
                        v.setWeight(sum/degree);
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        String file_name = "/Users/apoole/Desktop/gettysburg.txt";
        TextNetwork newNet = new TextNetwork();
        newNet.makeGraph(file_name);
//        System.out.print(newNet);
        //LinkedList<String> active = new LinkedList<String>();
        //active.add("A");
        //newNet.SpreadingActivation(3, active);
        System.out.print(newNet.getGraph().toString());
    }



}