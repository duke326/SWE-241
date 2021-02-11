package Exercise2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SIX {

    public static void main(String[] args) throws IOException {
        print(addWord(removeStopWords(scan(readFile("SWE-262/src/pride-and-prejudice.txt")))), args[0]);
    }

    static String readFile(String filePath) throws IOException {
        String data=null;
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str = null;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            String temp = str.toLowerCase();
            sb.append(temp + " ");
        }
        data=new String(sb.toString().trim()) ;
        return data;
    }

    static String[] scan(String data){
        String [] words=data.split("[ ,!.@#$%&*?\\\"'-]");
        return words;
    }
    static String [] removeStopWords(String[] words) throws IOException {
        File file = new File("SWE-262/src/stop-words.txt");
        Set<String >set=new HashSet<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str = null;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            String temp = str.toLowerCase();
            sb.append(temp + " ");
        }
        String[] stopWordsArray = sb.toString().trim().split(",");
        for(String word:stopWordsArray){
            set.add(word);
        }
        List<String > res=new ArrayList<>();
        for(int i=0;i<words.length;i++){
            if(!set.contains(words[i])){
                res.add(words[i]);
            }
        }
        String returnArray[]=new String[res.size()];
        System.out.println(res.size());
        return res.toArray(returnArray);
    }


    static  List<Map.Entry<String, Integer>> addWord(String[] words) throws IOException {
        List<Map.Entry<String, Integer>> word_freqs=new LinkedList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {//add to the map
            String temp = words[i].replaceAll("[^A-Za-z\\d+]", "");
            if ( temp != "" && !temp.equals("s")) {
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
        }
        List<Map.Entry<String,Integer>> list=new ArrayList<>();
        word_freqs = new LinkedList<>(map.entrySet());
        Collections.sort(word_freqs, (a, b) -> (b.getValue() - a.getValue()));
        return word_freqs;
    }




    static void print(List<Map.Entry<String, Integer>> word_freqs,String arg){
        for(int i=0;i<Integer.parseInt(arg);i++){
            if(word_freqs.get(i).getKey().length()>=2)
                System.out.println(word_freqs.get(i).getKey()+" "+word_freqs.get(i).getValue());
        }
    }
}
