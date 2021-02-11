package Exercise2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FIVE {
    //global variant
    static String data;
    static String [] words;
    static Set<String> stopWords;
    static List<Map.Entry<String, Integer>> word_freqs;


    public static void main(String[] args) throws IOException {
        readFile("SWE-262/src/pride-and-prejudice.txt");
        scan();
        stopWords();
        addWord();
        print();
    }

    static void scan(){
        words=data.split("[ ,!.@#$%&*?\\\"'-]");
    }

    static void readFile(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str = null;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            String temp = str.toLowerCase();
            sb.append(temp + " ");
        }
        data=new String(sb.toString().trim()) ;
    }

    static void stopWords() throws IOException {
        File file = new File("SWE-262/src/stop-words.txt");
        stopWords=new HashSet<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str = null;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            String temp = str.toLowerCase();
            sb.append(temp + " ");
        }
        String[] stopWordsArray = sb.toString().trim().split(",");
        for(String word:stopWordsArray){
            stopWords.add(word);
        }

    }

    static void addWord() throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {//add to the map
            String temp = words[i].replaceAll("[^A-Za-z\\d+]", "");
            if (!stopWords.contains(temp) && temp != "" && !temp.equals("s")) {
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
        }
        List<Map.Entry<String,Integer>> list=new ArrayList<>();
        word_freqs = new LinkedList<>(map.entrySet());
        Collections.sort(word_freqs, (a, b) -> (b.getValue() - a.getValue()));

    }
    static void print(){
        for(int i=0;i<25;i++){
            if(word_freqs.get(i).getKey().length()>=2)
                System.out.println(word_freqs.get(i).getKey()+" "+word_freqs.get(i).getValue());
        }
    }
}
