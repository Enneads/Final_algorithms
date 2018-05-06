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

    /**
     * This method makes a the network by taking a file name.
     * @param fileName
     * @throws IOException
     */

//    public void makeNetwork(String fileName) throws IOException {
//        ReadFile file = new ReadFile(fileName);
//        String[] text =  file.OpenFile();
//
//        for (String line : text) {
//            String[] words = cutString(line);
//            for (String word : words) {
//                for (String oWord : words)
//                    if (word.equals(oWord)) {
//                        continue; // it's telling me to get rid of this, but i want it to skip so that it doesn't compare a word to itself
//                    }
//                    else {
//                        graph.addEdge(new Vertex(word), new Vertex(oWord));
//                    }
//            }
//        }
//    }


    public WeightedGraph getGraph() {
        return graph;
    }public void setGraph(WeightedGraph graph) {
        this.graph = graph;
    }public void makeGraph(String fileName) throws IOException {
//        Map<Vertex, LinkedList<Edge>> mapp = new HashMap<>();
//        WeightedGraph wGraph = new WeightedGraph(mapp);
        ReadFile file = new ReadFile(fileName);
        String[] text = file.OpenFile();

        // parses through every line in text
        for (String line : text) {

            String tempStr = "";

            // make line uppercase
            // for(int j = 0; j < line.length(); j++) {//start character loop
            line = line.toUpperCase();

            // an array of words in a line
            String[] wordList = cutString(line);

            //
            for (String i : wordList) {
                for (String j : wordList) {
                    Edge e = new Edge(i, j);
                    graph.addEdge(e);
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
                    Vertex u = null;
                    for (Vertex v2:e.getEdge()){
                        if (!v2.equals(v)){
                            u = v2;
                        }
                    }

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
        System.out.print(newNet.getGraph().toString());
    }



}