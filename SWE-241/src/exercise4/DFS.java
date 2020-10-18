package exercise4;

import java.util.List;
import java.util.Map;

public class DFS {
    static boolean visited[]=new boolean[MainMethod1.buildMap().size()];
    //select A as start point
    public static void dfs(Map<Character, List<Character>> map, Character start){

        if(visited[start-'A']==true) return ;
        visited[start-'A']=true;
        System.out.print(start+"\t");
        for(Character ch:map.get(start)){
            dfs(map,ch);
        }



    }
}
