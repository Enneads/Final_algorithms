package Final;

import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


/**
 *  The {@code TextGraph} class represents an undirected, weighted graph that contains
 *  words read in from a text passage. Two vertices (words) are connected if they both
 *  occur in the same line in the passage. Also runs a spreading activation algorithm on
 *  a text graph. Runs these with a sample text passage in the main method.
 *  It supports the following primary operations: makeGraph and SpreadingActivation. It also
 *  provides methods for cutting a line into words, removing the most common English words 
 *  from a text passage, activating certain vertices before calling the spreading activation algo.
 *  <p>
 *
 *  @author Logan Stapleton
 *  @author Abigail Poole
 *  @author Shiyu Lin
 */
public class TextGraph {
    EdgeWeightedGraph graph;

    /**
     * Constructor initializes an empty EdgeWeightedGraph to be filled with words from a text passage in the makeGraph() method.
     */
    public TextGraph() {
        this.graph = new EdgeWeightedGraph(new LinkedList<Edge>(), new LinkedList<Vertex>());
    }

    /**
     * Overloaded constructor initializes a text graph from an already existing EdgeWeightedGraph {@code graph}
     *
     * @param graph an EdgeWeightedGraph to include into a text graph
     */
    public TextGraph(EdgeWeightedGraph graph) {
        this.graph = graph;
    }

    /**
     * Returns the EdgeWeightedGraph in this TextGraph
     *
     * @return graph this EdgeWeightedGraph in the text graph
     */
    public EdgeWeightedGraph getGraph() {
        return graph;
    }

    /**
     * Set the EdgeWeightedGraph in this TextGraph
     *
     * @param graph the EdgeWeightedGraph to set into text graph
     */
    public void setGraph(EdgeWeightedGraph graph) {
        this.graph = graph;
    }

    /**
     * Returns an array of strings of words in line of text.
     *
     * @param line the string of the line to be split into words
     * @return an array of strings of all the words in a line
     */
    private String[] cutString(String line){
        return line.split("\\W+");
    }

    /**
     * Checks whether a string is in an array of strings
     *
     * @param str a string to be found
     * @param arry an array of strings to check if {@code str} is in
     * @return true if {@code str} is in {@code arry}, false if not.
     */
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

    /**
     * Reads in a text file and updates this TextGraph with a network of words.
     * Two words are connected if they lie in the same line.
     * The edge-weight between words equals the number of lines the words share.
     *
     * @param fileName a string with the name of a file to be read into the text graph
     */
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

    /**
     * Activates vertices with names in the list {@code active},
     * (where activating a vertex is setting the node-weight to 1).
     *
     * @param active a list of strings of the names of vertices to activate
     */
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

    /**
     * Runs a spreading activation algorithm on this text graph.
     * Note: each iteration parses over every node in the graph and updates
     *  the node-weight based on a function of the node's current weight, the
     *  weights of its neighbor nodes, and the edge-weight of the edges connecting
     *  to those neighbor nodes. Iterates as many times as specified.
     *
     * @param iterTime the number of iterations to run spreading algorithm
     */
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

    /**
     * Takes in a list of vertices and returns a new list of the same vertices,
     * sorted by node-weight from heaviest to lightest. (Bubble sort).
     *
     * @param vertices a list of vertices to be sorted
     * @return the list of vertices in order from highest node-weight to lowest
     */
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

    /**
     * Helper method to getSortedVertices that swaps the position of two vertices.
     *
     * @param list a list of vertices
     * @param posiOne the position of one vertex
     * @param posiTwo the position of the other vertex
     */
    private void swapVer(LinkedList<Vertex> list, int posiOne, int posiTwo){
        Vertex tempVer = list.get(posiOne);
        list.set(posiOne,list.get(posiTwo));
        list.set(posiTwo,tempVer);
    }

    /**
     * The driver: creates a sample TextGraph out of a text file of posts from
     * the anti-black forum chimpmania.com, activates the word "NIGGER", then runs
     * a spreading activation algorithm on this graph. Outputs a sorted list of nodes
     * and two files --chimpmaniaNodes.csv and chimpmaniaEdges.csv-- that may be 
     * input into Gephi to create a network with this updated graph.
     */
    public static void main(String[] args) throws IOException {
        String file_name = "Final/text_files/chimpmania.txt";
        ReadFile file = new ReadFile(file_name);
        String[] text = file.OpenFile();

        TextGraph abcGraph = new TextGraph();
        abcGraph.makeGraph(file_name);
        LinkedList<String> active = new LinkedList<String>();
        active.add("NIGGER");
        abcGraph.activate(active);

        abcGraph.SpreadingActivation(5);
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
