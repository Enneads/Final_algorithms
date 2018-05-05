//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Final;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkProg {
    private Map map = new HashMap();
    WeightedGraph graph;

    public NetworkProg() {
        this.graph = new WeightedGraph(this.map);
    }


    public Map<String, Integer> makeNetwork(){


    }


    private String[] cutString(String line){
//        String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
//                "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
//        String sub = "";
//        for (int i = 0; i<line.length(); i++){
//            char ch = line.charAt(i);
//            if(!Arrays.asList(alphabet).contains( Character.toString(ch) )){
//                if (!sub.isEmpty()){
//
//                }
//            }
//        }
        String[] cuttedLine = line.split("\\W+");
        return cuttedLine;
    }
}
