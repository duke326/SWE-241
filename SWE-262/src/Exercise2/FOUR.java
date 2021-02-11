package Exercise2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FOUR {
    public static void main(String[] args) throws IOException {
        String data;
        String [] words;
        Set<String> stopWords;
        List<Map.Entry<String, Integer>> word_freqs;
        Set<Character> validWord=new HashSet<>();
        for(int i=0;i<26;i++){
            validWord.add((char)('a'+i));
        }

        File file = new File("SWE-262/src/pride-and-prejudice.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str = null;

        List<String > wordList=new ArrayList<>();
        while ((str = br.readLine()) != null) {
            String temp = str.toLowerCase();
            temp += " ";
            StringBuilder sb = new StringBuilder();
            char chs[]=temp.toCharArray();
            for(char ch:chs){
                if(validWord.contains(ch)){
                    sb.append(ch);
                }
                else{
                    wordList.add(sb.toString());
                    sb=new StringBuilder();
                }
            }

        }
        words=wordList.toArray(new String[wordList.size()]);
        File file2 = new File("SWE-262/src/stop-words.txt");
        stopWords=new HashSet<>();
        BufferedReader br2 = new BufferedReader(new FileReader(file2));
        String str2 = null;
        StringBuilder sb2 = new StringBuilder();
        while ((str2 = br2.readLine()) != null) {
            String temp = str2.toLowerCase();
            sb2.append(temp + " ");
        }
        String[] stopWordsArray = sb2.toString().trim().split(",");
        for(String word:stopWordsArray){
            stopWords.add(word);

        }
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
        for(int i=0;i<Integer.parseInt(args[0]);i++){
            if(word_freqs.get(i).getKey().length()>=2)
                System.out.println(word_freqs.get(i).getKey()+" "+word_freqs.get(i).getValue());
        }
    }
}
