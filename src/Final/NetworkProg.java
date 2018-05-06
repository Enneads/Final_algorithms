//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Final;

import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class NetworkProg {
    private Map map = new HashMap();
    WeightedGraph graph;

    public NetworkProg() {
        this.graph = new WeightedGraph(this.map);
    }

    public void makeNetwork(String fileName) throws IOException {
        ReadFile file = new ReadFile(fileName);
        String[] text =  file.OpenFile(); // fix how this exception is handled


        for (String line : text) {
            String[] words = cutString(line);
            for (String word : words) {
                for (String oWord : words)
                    if (word.equals(oWord)) {
                        continue; // it's telling me to get rid of this, but i want it to skip so that it doesn't compare a word to itself
                    }
                    else {
                        graph.addEdge(new Node(word), new Node(oWord));
                    }
            }


        }

    }


    private String[] cutString(String line){

//        String[] cuttedLine = line.split("\\W+");
        return line.split("\\W+");
    }

    public static void main(String[] args) throws IOException {
        String file_name = "/Users/linshiyu/Desktop/gettysburg.txt";
        NetworkProg newNet = new NetworkProg();
        newNet.makeNetwork(file_name);
        System.out.print(newNet);
    }



}



// THERE'S NO WAY TO KNOW WHICH NODES ARE IN THE GRAPH (maybe add a graph object)
