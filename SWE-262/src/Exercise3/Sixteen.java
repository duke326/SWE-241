import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Sixteen {
    public static void main(String[] args) throws IOException {

    EventBus eventBus=new EventBus();
    StopWordLoader stopWordLoader=new StopWordLoader(EventBus.WORD_LOADER);
    WordLoader wordLoader=new WordLoader(EventBus.FREQUENCY_MANAGER, args[0]);
    FrequentManager frequentManager=new FrequentManager(EventBus.ZCOUNTER);
    Zcounter zcounter=new Zcounter();
    stopWordLoader.update(null,null);

    }


}

interface MyObserver {
    Object update();
}


class EventBus{

    public final static int WORD_LOADER=0;
    public final static int STOPWORD_LOADER=1;
    public final static int FREQUENCY_MANAGER=2;
    public final static int ZCOUNTER=3;
    static Event []aEvent;

    class Event extends Observable {

        public void setChanged() {
            super.setChanged();
        }

    }
    public EventBus (){
        aEvent=new Event[4];
        for(int i=0;i<4;i++){
            aEvent[i]=new Event();
        }
    }
    //
    static public void subscribe(int type, Observer observer) {
        aEvent[type].addObserver(observer);
    }
    static public void announce(int type, Object object) {
        aEvent[type].setChanged();
        aEvent[type].notifyObservers(object);
    }

}


class StopWordLoader implements Observer {

    static int OutComeCode;
    private Set<String> stopWords;

    public  StopWordLoader(int code){
        EventBus.subscribe(EventBus.STOPWORD_LOADER, this);
        OutComeCode=code;
    }

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
        String[] stopWords = readFile("SWE-262/src/stop-words.txt").split(",");
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



    @Override
    public void update(Observable o, Object arg) {
        try {
            addStopWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EventBus.announce(OutComeCode,(Object) stopWords);
    }
}

class WordLoader implements Observer {
    static int OutComeCode;
    static String url;
    private Map<String, Integer> wordList;

    public  WordLoader(int code, String url){
        EventBus.subscribe(EventBus.WORD_LOADER, this);
        OutComeCode=code;
        this.url=url;
    }

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
        wordList = new HashMap<>();
        String content = readFile(path);
        String[] words = content.split("[ ,!.@#$%&*?\\\"'-]");
        for (int i = 0; i < words.length; i++) {//add to the map
            String temp = words[i].replaceAll("[^A-Za-z\\d+]", "");
            if (!stopWords.contains(temp) && temp.length() >= 2 && !temp.equals("s")) {
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



    @Override
    public void update(Observable o, Object arg) {
        try {
            addWord((Set<String>)arg,url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        EventBus.announce(EventBus.FREQUENCY_MANAGER, (Object) wordList);

    }
}




class FrequentManager implements Observer {
    static int OutComeCode;
    private List<Map.Entry<String, Integer>> frequent;

    public  FrequentManager(int code){
        EventBus.subscribe(EventBus.FREQUENCY_MANAGER, this);
        OutComeCode=code;
    }

    void sort(Map<String, Integer> map) {
        frequent = new ArrayList<>(map.entrySet());
        Collections.sort(frequent, (a, b) -> (b.getValue() - a.getValue()));
    }

    public List<Map.Entry<String, Integer>> getFrequent() {
        return frequent;
    }

    public void setFrequent(List<Map.Entry<String, Integer>> frequent) {
        this.frequent = frequent;
    }


    @Override
    public void update(Observable o, Object arg) {
        sort((Map<String, Integer>)arg);
                for (int i = 0; i < 25; i++) {
            System.out.println(frequent.get(i).getKey() + " " + frequent.get(i).getValue());
        }
        EventBus.announce(EventBus.ZCOUNTER, (Object) frequent);
    }
}


class Zcounter implements Observer {
    static int OutComeCode;
    //private List<Map.Entry<String, Integer>> frequent;

    public  Zcounter(){
        EventBus.subscribe(EventBus.ZCOUNTER, this);

    }

    int countZ(List<Map.Entry<String, Integer>> list) {
            int count=0;
            for(int i=0;i<list.size();i++){
                if(list.get(i).getKey().indexOf('z')!=-1){
                    count+=list.get(i).getValue();
                }
            }
        return count;
    }


    @Override
    public void update(Observable o, Object arg) {
        int res=countZ((List<Map.Entry<String, Integer>>)arg);
        System.out.println("the number of words contains z is "+ res);

    }
}
