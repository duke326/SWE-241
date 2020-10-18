package exercise3;

import java.util.List;
import java.util.Map;

public class AListToIMatrix {
    int [][]incidenceMatrix;
    public void convert(Map<Integer, List<Integer>> map){
        int nodeNum=map.size();
        int edgeNum=numEdge(map);
        Edge[] edges=readEdge(map);
        incidenceMatrix=new int [nodeNum][edgeNum];
        int counter=0;
        for(int i=0;i<nodeNum;i++){
            List<Integer> temp=map.get(i);
            for(int j=0;j< temp.size();j++){
                //temp2 is used to call the method
                Edge temp2=new Edge(0,0);

                counter=temp2.exists(i,temp.get(j),edges);
                if(counter!=-1){
                    incidenceMatrix[i][counter]=1;
                }
            }
        }
        for(int i=0;i<nodeNum;i++){
            for(int j=0;j<edgeNum;j++){
                System.out.print( incidenceMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
    public static int numEdge(Map<Integer, List<Integer>> map){
        int res=0;
        for(Integer key:map.keySet()){
            res+=map.get(key).size();
        }
        return res/2;
    }
    public Edge[] readEdge(Map<Integer, List<Integer>> map){
        Edge[] edges=new Edge[numEdge(map)];

        int counter=0;
        for (int i=0;i<edges.length;i++){
            edges[i]=new Edge(0,0);
        }
        for(int i=0;i<map.size();i++){
            List<Integer> temp=map.get(i);
            for(int j=0;j<temp.size();j++){
                Edge edge=new Edge(i,temp.get(j));
                //prevent  duplicate
                if(edge.exists(i,temp.get(j), edges)==-1){
                    edges[counter]=edge;
                    counter++;
                }
            }
        }
        return edges;
    }

}

class Edge{
    int vstart;
    int vend;
    public  Edge(int vstart, int vend){
        this.vstart=vstart;
        this.vend=vend;
    }

    public int getVstart() {
        return this.vstart;
    }

    public int getVend() {
        return this.vend;
    }

    public  int  exists(int a, int b,  Edge[] nodes){
        for(int i=0;i<nodes.length;i++){
            if(nodes[i].getVstart()==a){
                if(nodes[i].getVend()==b){
                    return i;
                }
            }
            else if(nodes[i].getVstart()==b){
                if(nodes[i].getVend()==a){
                    return i;
                }
            }
        }
        return -1;
    }
}
