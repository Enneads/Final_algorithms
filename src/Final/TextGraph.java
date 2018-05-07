package Final;

import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.io.IOException;
import java.util.*;

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

    private boolean contains(String str, String[] arry) {
        boolean found = false;
        for (String s : arry) {
            if (s.equals(str)) {
                found = true;
            }
        }
        return found;
    }

    private String[] makeDistinct(String[] arr) {
//        StringArray newArr = new StringArray();
        String str = "";
        for (String s : arr) {
            s = s + " ";
            if (!str.contains(s)) {
                str = str.concat(s);
            }
        }

        return cutString(str);

//        String[] newArr = null;
//        for (int i = 0; i < arr.length; i++) {
//            if (!contains(arr[i], newArr)) {
//                newArr[newArr.length] = arr[i];
//            }
//        }
    }

    public void makeGraph(String fileName) throws IOException {
        ReadFile file = new ReadFile(fileName);
        String[] text = file.OpenFile();

        // parses through every line in text
        for (String line : text) {
            line = line.toUpperCase(); // make line uppercase

            // an array of words in a line
            String[] wordList = cutString(line);
//            for (String w : wordList) {
//            }
////                System.out.println(w);

            ArrayList<String> lineList = new ArrayList<String>();

            wordList = makeDistinct(wordList);
//            for (String w : wordList) {
//                System.out.println(w);
//            }

            for (int i = 0; i < wordList.length - 1; i++) {
                for (int j = i + 1; j < wordList.length; j++) {
                    graph.addEdge(wordList[i], wordList[j]);
                }
            }





            // parse through every word in line]
//            for (String i : wordList) {
//                if(!lineList.contains(i)) {
//                    lineList.add(i);
//                }
//
////                if (lineList.isEmpty()) {
////                    lineList.add(i);
////                } else {
////                    for (String s : lineList) {
////                        if (!s.equals(i)) {
////                            lineList.add(s);
////                        }
//
//                        for (String j : lineList) {
//                            //System.out.println(j);
//                            graph.addEdge(i, j);
//                        }
//                    }
                }
            }

    public void SpreadingActivation(int iterTime, LinkedList<String> active) {

        Map<Vertex, LinkedList<Edge>> graphMap = new HashMap<Vertex, LinkedList<Edge>>();

        for (Vertex v : graph.getVertices()) {
            graphMap.put(v, graph.adjacentTo(v));
        }

        Map<Vertex, LinkedList<Edge>> network = graphMap;

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
        String file_name = "/Users/lstaplet/Desktop/chimpmania.txt";
        TextGraph abcGraph = new TextGraph();
        abcGraph.makeGraph(file_name);
//        System.out.print(newNet);
        LinkedList<String> active = new LinkedList<String>();
        active.add("A");
        abcGraph.SpreadingActivation(12, active);
        System.out.print(abcGraph.getGraph().toString());
    }
}// end TextGraph class
