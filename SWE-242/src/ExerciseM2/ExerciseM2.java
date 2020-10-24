package ExerciseM2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ExerciseM2 {
    public static int  ReadFromFile(String source) throws IOException {
        int count=0;
        //String offset="SWE-242/src/ExerciseM2/";
        File file=new File(source);

        BufferedReader br=new BufferedReader(new FileReader(file));
        String str;
        while((str=br.readLine())!=null){
            count++;
        }
        return count;
    }
    public static void main(String[] args) {
        for(String str:args){
            String name=str.substring(str.lastIndexOf("/")+1);

            try {
                System.out.println(name+" has "+ReadFromFile(str)+" lines");
            } catch (IOException e) {
                System.out.println(str+" File doesn't exist");
            }
        }

    }

}

