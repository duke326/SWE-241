package exercise3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ImatrixToAList {
    public void convert(int [][] matrix){
        boolean isFirst=true;
        int nodeNum=matrix.length;
        int edgeNum=matrix[0].length;
        Map<Integer, List<Integer>>map=new HashMap<>();
        for(int i=0;i<nodeNum;i++){
            map.put(i,new LinkedList<Integer>());
        }

        for(int i=0;i<edgeNum;i++){
            int edge1 = 0;
            int edge2=0;
            for(int j=0;j<nodeNum;j++){
                if(matrix[j][i]==1){
                    if(isFirst){
                        edge1=j;

                    }
                    else{
                        edge2=j;
                    }
                    isFirst=!isFirst;
                }


            }
            List<Integer> list1=map.get(edge1);
            List<Integer> list2=map.get(edge2);
            list1.add(edge2);
            list2.add(edge1);
            map.put(edge1, list1);
            map.put(edge2,list2);
            isFirst=true;
        }
        for(int i:map.keySet()){
            System.out.println(i+" Connect "+map.get(i));
            //System.out.println(AListToIMatrix.numEdge(map));
        }
    }
}
