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
////                System.out.println(w);
//            }

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



    public static void main(String[] args) throws IOException {
        String file_name = "/Users/apoole/Desktop/gettysburg.txt";
        TextGraph abcGraph = new TextGraph();
        abcGraph.makeGraph(file_name);
//        System.out.print(newNet);
        //LinkedList<String> active = new LinkedList<String>();
        //active.add("A");
        //newNet.SpreadingActivation(3, active);
        System.out.print(abcGraph.getGraph().toString());
    }
}// end TextGraph class
