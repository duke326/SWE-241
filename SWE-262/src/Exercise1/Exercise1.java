package Exercise1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Exercise1 {
    public static void main(String[] args) throws IOException {
        String content = readFile("SWE-262/src/pride-and-prejudice.txt");
        Set<String> stopWords = stopWords();
        String[] words = content.split("[ ,!.@#$%&*?\\\"'-]");
        List<Map.Entry<String, Integer>> map = addWord(words, stopWords);
        for(int i=0;i<25;i++){
            System.out.println(map.get(i).getKey()+" "+map.get(i).getValue());
        }
    }

    static String readFile(String filePath) throws IOException {
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

    static Set<String> stopWords() throws IOException {
        Set<String> set = new HashSet<>();
        String[] stopWords = readFile("SWE-262/src/stop-words.txt").split(",");
        for (String str : stopWords) {
            set.add(str);
        }
        return set;

    }

    static List<Map.Entry<String, Integer>> addWord(String[] words, Set<String> stopWords) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {//add to the map
            String temp = words[i].replaceAll("[^A-Za-z\\d+]", "");
            if (!stopWords.contains(temp) && temp != "" && !temp.equals("s")) {
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, (a, b) -> (b.getValue() - a.getValue()));


        return list;
    }
}
