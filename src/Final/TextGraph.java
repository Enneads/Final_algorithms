package Final;

import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

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
        return line.split("\\W+");
    }

    private boolean contains(String str, String[] arry) {
        boolean found = false;
        for (String s : arry) {
            if (s.equals(str)) {
                found = true;
            }
        }
        return found;
    }

    /**
    * Removes trivial words from a line of text.
    * (Trivial words are those contained in the list of
    * the 1000 most common English words.)
    *
    * @param line a String, a line of text to modify
    * @return an array of words in the line sans common words
    */
    private String[] removeTrivial(String line) throws IOException {
        ReadFile file = new ReadFile("/Users/lstaplet/Desktop/common.txt");
        String[] common = file.OpenFile();
        String str = "";

        // common to uppercase
        for (int i=0; i < common.length; i++) {
            common[i] = common[i].toUpperCase();
            //System.out.println(common[i]);
        }

        // cast common as a list
        List<String> commonList = Arrays.asList(common);
        String[] arry = cutString(line);

        // arry to uppercase
        for (int i=0; i < arry.length; i++) {
            String word = arry[i].toUpperCase();

            boolean inCommon = false;
            for (String check : commonList) {
                if (word.equals(check)) {
                    inCommon = true;
                }
            }
            if (!inCommon){
                str = str.concat(word + " ");
            }
        }
        return cutString(str);
    }// end removeTrivial method

    private String[] makeDistinct(String[] arr) {
        String str = "";
        for (String s : arr) {
            s = s + " ";
            if (!str.contains(s)) {
                str = str.concat(s);
            }
        }
        return cutString(str);
    }

    public void makeGraph(String fileName) throws IOException {
        ReadFile file = new ReadFile(fileName);
        String[] text = file.OpenFile();

        // parses through every line in text
        for (String line : text) {
            line = line.toUpperCase(); // make line uppercase
            String[] wordList = removeTrivial(line); // an array of words in a line (w/o common)

//            for (String w : wordList) {
//            }
////                System.out.println(w);

            ArrayList<String> lineList = new ArrayList<String>();

            wordList = makeDistinct(wordList);
//            wordList = removeTrivial(wordList);
//            for (String w : wordList) {
//                System.out.println(w);
//            }

            for (int i = 0; i < wordList.length - 1; i++) {
                for (int j = i + 1; j < wordList.length; j++) {
                    graph.addEdge(wordList[i], wordList[j]);
                }
            }
         }
     }// end makeGraph method

    public void activate(LinkedList<String> active){
        // Set activated vertices' weight (those in active list) to 1.
        for (Vertex v : graph.getVertices()) { // loops over every vertex in graph
            Iterator iter = active.iterator();
            while (iter.hasNext()) { // loops over every word in active
                String word = (String) iter.next();
                if (v.getKey().equals(word)) { // checks if vertex key is in active
                    v.setWeight(1D);
                }
            }
        }
    }// end activate method


    public void SpreadingActivation(int iterTime) {
        EdgeWeightedGraph updatedGraph = graph.clone();

        for (int i=0; i<iterTime; i++) {
            // spread
            for (Vertex v : graph.getVertices()) {
                int degree = 0;
                double sum = 0;

                LinkedList<Edge> adjEdges = graph.adjacentTo(v);
                for (Edge e : adjEdges) {
                    Vertex u = e.getTarget(v);
                    sum += u.getWeight() * Math.exp(-1 / (30*e.getWeight()));
                    degree++;
                }

                if (v.getWeight() != 1D) {
                    double currentWeight = v.getWeight();
                    double neighborWeight = sum / degree;
                    updatedGraph.getVertex(v.getKey()).setWeight((currentWeight + neighborWeight) / 2);
                }
            }
            // update graph after every iteration
            graph = updatedGraph;
        }
    }// end spreading activation method


    public LinkedList<Vertex> getSortedVertices(LinkedList<Vertex> vertices){
        int loop = vertices.size();
        for(int i = 0; i < loop - 1; i++){
            boolean swap = false;
            for(int j = 0; j < loop - 1; j++){
                if (vertices.get(j).getWeight() < vertices.get(j+1).getWeight()){
                    swapVer(vertices, j, j+1);
                    swap = true;
                }
            }
            if(!swap){
                break;
            }
        }
        return vertices;
    }// end getSortedVertices method

    private void swapVer(LinkedList<Vertex> list, int posiOne, int posiTwo){
        Vertex tempVer = list.get(posiOne);
        list.set(posiOne,list.get(posiTwo));
        list.set(posiTwo,tempVer);
    }


    public static void main(String[] args) throws IOException {
        String file_name = "chimpmania.txt";
        ReadFile file = new ReadFile(file_name);
        String[] text = file.OpenFile();

        TextGraph abcGraph = new TextGraph();
        abcGraph.makeGraph(file_name);
        LinkedList<String> active = new LinkedList<String>();
        active.add("NIGGER");
        abcGraph.activate(active);

        abcGraph.SpreadingActivation(1);
        LinkedList<Vertex> vertexList = abcGraph.getSortedVertices(abcGraph.getGraph().getVertices());
        System.out.print(vertexList);

        PrintWriter pwNodes = new PrintWriter(new File("chimpmaniaNodes.csv"));
        StringBuilder sbNodes = new StringBuilder();
        sbNodes.append("Id,Label,Weight\n");
        for (Vertex v : vertexList){
            sbNodes.append(v.toString());
        }
        pwNodes.write(sbNodes.toString());
        pwNodes.close();

        LinkedList<Edge> edgeList = abcGraph.getGraph().getEdges();

        PrintWriter pwEdges = new PrintWriter(new File("chimpmaniaEdges.csv"));
        StringBuilder sbEdges = new StringBuilder();
        sbEdges.append("Source,Target,Weight\n");
        for (Edge e : edgeList){
            sbEdges.append(e.toString());
        }
        pwEdges.write(sbEdges.toString());
        pwEdges.close();
    }// end main
}// end TextGraph class
