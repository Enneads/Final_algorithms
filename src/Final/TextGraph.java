package Final;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class TextGraph {
    EdgeWeightedGraph graph;

    public TextGraph() {
        this.graph = new EdgeWeightedGraph(new LinkedList<Edge>(), new LinkedList<Vertex>());
    }

    public TextGraph(EdgeWeightedGraph graph) {
        this.graph = graph;
    }

    public EdgeWeightedGraph getGraph() {
        return graph;
    }

    public void setGraph(EdgeWeightedGraph graph) {
        this.graph = graph;
    }

    private String[] cutString(String line){
    // String[] cutLine = line.split("\\W+");
        return line.split("\\W+");
    }

    public void makeGraph(String fileName) throws IOException {
        ReadFile file = new ReadFile(fileName);
        String[] text = file.OpenFile();

        // parses through every line in text
        for (String line : text) {
            line = line.toUpperCase(); // make line uppercase

            // an array of words in a line
            String[] wordList = cutString(line);

            LinkedList<String> inLine = new LinkedList<String>();

            // parse through every word in line
            for (String i : wordList) {
                if(!inLine.contains(i)){
                    inLine.add(i);
                }
                for (String j : inLine) {
                    graph.addEdge(i, j);
                }
            }
        }
    }
}
