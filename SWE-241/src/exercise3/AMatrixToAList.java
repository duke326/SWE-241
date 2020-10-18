package exercise3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AMatrixToAList {
    int row;
    int col;
    static Map<Integer, List<Integer>> map;
    public  void convert(int matrix[][]){
        row=matrix.length;
        col=matrix[0].length;
        map=new HashMap<>();
        for(int i=0;i<row;i++){
            List<Integer> temp=new ArrayList<>();
            map.put(i, temp);
        }
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(matrix[i][j]==1){
                    List<Integer> temp=map.get(i);
                    temp.add(j);
                    map.put(i,temp);
                }
            }
        }
        for(int i:map.keySet()){
            System.out.println(i+" Connect "+map.get(i));
            //System.out.println(AListToIMatrix.numEdge(map));
        }
    }



}
