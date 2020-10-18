package exercise4;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFS {
    static boolean visited[];
    public static void main(String[] args) {

        Map<Character, List<Character>> map= MainMethod1.buildMap();

        bfs(map, 'A');//select A as start point
    }

    public static void bfs(Map<Character, List<Character>> map, Character start){
        visited=new boolean[map.size()];
        Queue<Character> q=new ArrayDeque();
        q.offer(start);
        visited[start-'A']=true;
        while(!q.isEmpty()){
            Character curr=q.poll();
            System.out.print(curr+"\t");
            List<Character> temp=map.get(curr);
            for(Character ch:temp){
                if(!visited[ch-'A']){
                    q.offer(ch);
                    visited[ch-'A']=true;
                }
            }

        }
    }
}
