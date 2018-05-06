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

    public void makeNetwork(String fileName) throws IOException {
        ReadFile file = new ReadFile(fileName);
        String[] text =  file.OpenFile();

        for (String line : text) {
            String[] words = cutString(line);
            for (String word : words) {
                for (String oWord : words)
                    if (word.equals(oWord)) {
                        continue; // it's telling me to get rid of this, but i want it to skip so that it doesn't compare a word to itself
                    }
                    else {
                        graph.addEdge(new Vertex(word), new Vertex(oWord));
                    }
            }
        }
    }

    public void makeGraph(String fileName) throws IOException {
        Map<Vertex, LinkedList<Edge>> mapp = new HashMap<>();
        WeightedGraph wGraph = new WeightedGraph(mapp);
        ReadFile file = new ReadFile(fileName);
        String[] text = file.OpenFile();

        for (String line : text) {
            String[] wordList = cutString(line);

            for (String i : wordList) {
                for (String j : wordList) {
                    wGraph.addEdge(i, j);
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

//    public void SpreadingActivation(int iterTime, String active){
//        int degree = 0;
//        double sum = 0;
//        for (v: )
//    }


    public static void main(String[] args) throws IOException {
        String file_name = "/Users/apoole/Desktop/gettysburg.txt";
        TextNetwork newNet = new TextNetwork();
        newNet.makeNetwork(file_name);
        System.out.print(newNet);
    }



}