package exercise4;

import java.util.*;

public class MainMethod1 {
    //static boolean visited[];
    public static void main(String[] args) {
        Map<Character, List<Character>> map=buildMap();
        System.out.println("The result of DFS");
        DFS.dfs(map,'A');
        System.out.println("\n"+"The result of BFS");
        BFS.bfs(map,'A');

    }
    public static Map<Character, List<Character>> buildMap(){
        Map<Character, List<Character>> map=new HashMap<>();
        for(char i='A';i<='J';i++){
            map.put(i, new LinkedList<>());
        }
        map.get('A').add('B');
        map.get('A').add('D');
        map.get('A').add('I');
        map.get('B').add('A');
        map.get('B').add('D');
        map.get('B').add('E');
        map.get('B').add('C');
        map.get('C').add('B');
        map.get('C').add('E');
        map.get('C').add('F');
        map.get('D').add('A');
        map.get('D').add('B');
        map.get('D').add('E');
        map.get('D').add('G');
        map.get('E').add('B');
        map.get('E').add('C');
        map.get('E').add('D');
        map.get('E').add('F');
        map.get('E').add('G');
        map.get('E').add('H');
        map.get('F').add('C');
        map.get('F').add('E');
        map.get('F').add('H');
        map.get('G').add('D');
        map.get('G').add('E');
        map.get('G').add('H');
        map.get('G').add('I');
        map.get('G').add('J');
        map.get('H').add('F');
        map.get('H').add('E');
        map.get('H').add('G');
        map.get('H').add('J');
        map.get('I').add('A');
        map.get('I').add('G');
        map.get('I').add('J');
        map.get('J').add('I');
        map.get('J').add('G');
        map.get('J').add('H');
        for(Character i:map.keySet()){
            Collections.sort(map.get(i));
            //System.out.println(i+" Connect "+ map.get(i));
            //System.out.println(AListToIMatrix.numEdge(map));
        }
        return map;
    }

}
