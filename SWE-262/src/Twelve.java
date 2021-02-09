package Exercise3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Twelve {
    public static void main(String[] args) throws IOException {
        WordLoader wordLoader=new WordLoader();
        StopWordLoader stopWordLoader=new StopWordLoader();
        FrequentManager frequentManager=new FrequentManager();
        stopWordLoader.addStopWords();
        wordLoader.addWord(stopWordLoader.getStopWords(),args[0]);
        frequentManager.sort(wordLoader.getWordList());

        for(int i=0;i<25;i++){
            System.out.println(frequentManager.getFrequent().get(i).getKey()+" "+frequentManager.getFrequent().get(i).getValue());
        }
    }



}
class WordLoader{

    private Map<String, Integer> wordList;

    String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str = null;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            String temp = str.toLowerCase();
            sb.append(temp + " ");
        }
        return sb.toString().trim();
    }
    void addWord(Set<String> stopWords, String path) throws IOException {
        wordList=new HashMap<>();
        String content = readFile(path);
        String[] words = content.split("[ ,!.@#$%&*?\\\"'-]");
        for (int i = 0; i < words.length; i++) {//add to the map
            String temp = words[i].replaceAll("[^A-Za-z\\d+]", "");
            if (!stopWords.contains(temp) && temp.length()>=2 && !temp.equals("s")) {
                wordList.put(temp, wordList.getOrDefault(temp, 0) + 1);
            }
        }
    }

    public Map<String, Integer> getWordList() {
        return wordList;
    }

    public void setWordList(Map<String, Integer> wordList) {
        this.wordList = wordList;
    }
}


class StopWordLoader{
    private Set<String> stopWords;

    String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str = null;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            String temp = str.toLowerCase();
            sb.append(temp + " ");
        }
        return sb.toString().trim();
    }

    void addStopWords() throws IOException {
        Set<String> set = new HashSet<>();
        String[] stopWords = readFile("../stop-words.txt").split(",");
        for (String str : stopWords) {
            set.add(str);
        }
        setStopWords(set);
    }

    public Set<String> getStopWords() {
        return stopWords;
    }

    public void setStopWords(Set<String> stopWords) {
        this.stopWords = stopWords;
    }
}
class FrequentManager{
    private List<Map.Entry<String ,Integer>> frequent;
    void sort(Map<String ,Integer> map){
        frequent=new ArrayList<>(map.entrySet());
        Collections.sort(frequent, (a,b)->(b.getValue()-a.getValue()));
    }

    public List<Map.Entry<String, Integer>> getFrequent() {
        return frequent;
    }

    public void setFrequent(List<Map.Entry<String, Integer>> frequent) {
        this.frequent = frequent;
    }
}
