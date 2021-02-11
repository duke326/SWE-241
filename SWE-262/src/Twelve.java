import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Twelve {
    static HashMap<String , Object> wordLoader=new HashMap<>(){
        {
            put("data", new ArrayList<>());
            put("init", new WordLoader());
            put("acticeWordLoader",(Action)(obj,arguments)->(wordLoader.get("data")));
            put("print", new Print());
        }

    };
    static Map<String, Object> stopWordLoader=new HashMap<>(){
        {
            put("data", new HashSet());
            put("init", new StopWordLoader());
            put("acticeStopWordLoader",(Action)(map, arguments)->(stopWordLoader.get("data")));

        }
    };


    public static void main(String[] args) throws IOException {
        Map<String,Integer> map=new HashMap<>();
        ((Action)wordLoader.get("init")).execute(wordLoader,new String[]{args[0]});
        List<String> words=(List<String>)((Action)wordLoader.get("acticeWordLoader")).execute(wordLoader, null);
        //new Object[]{args[0]}
        for(String str:words){
            map.put(str, map.getOrDefault(str,0)+1);
        }
        Map<String, Object> newMap=new HashMap<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()){

            newMap.put(entry.getKey(), (Object)entry.getValue().toString());
        }
        ((Action)wordLoader.get("print")).execute(newMap,null);

    }

    interface Action{
        Object execute(Map<String, Object> obj, Object arguments[]);

    }
    static class Print implements Action{

        @Override
        public Object execute(Map<String, Object> map, Object[] arguments) {
            Map<String, Integer> newMap=new HashMap<>();
            for(Map.Entry<String, Object> entry : map.entrySet()){
                newMap.put(entry.getKey(), Integer.valueOf(entry.getValue().toString()));
            }
            List<Map.Entry<String,Integer>> list=new ArrayList<>(newMap.entrySet());
            Collections.sort(list, (a, b) -> (b.getValue() - a.getValue()));
            for(int i=0;i<25;i++){
                System.out.println(list.get(i).getKey()+" "+list.get(i).getValue());
            }
            return null;
        }
    }

    static class WordLoader implements  Action{

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


        public Map<String, Integer> getWordList() {
            return wordList;
        }

        public void setWordList(Map<String, Integer> wordList) {
            this.wordList = wordList;
        }

        @Override
        public Object execute(Map<String, Object> obj, Object[] arguments) {
            String path=arguments[0].toString();
            List<String> res=(List<String>) obj.get("data");
            ((Action)stopWordLoader.get("init")).execute(stopWordLoader,null);

            String content = null;
            try {
                content = readFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] words = content.split("[ ,!.@#$%&*?\\\"'-]");

            Set<String> stopWords=(Set<String>) ((Action)stopWordLoader.get("acticeStopWordLoader")).execute(stopWordLoader, null);
            for (int i = 0; i < words.length; i++) {//add to the map
                String temp = words[i].replaceAll("[^A-Za-z\\d+]", "");
                if (!stopWords.contains(temp) && temp.length()>=2 && !temp.equals("s")) {
                    res.add(temp);
                }
            }
            System.out.println(res.size());

            return null;
        }
    }


    static class StopWordLoader implements Action{
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

        @Override
        public Object execute(Map<String, Object> obj, Object[] arguments) {

                Set<String> set= (Set<String>) obj.get("data");
            String[] stopWords = new String[0];
            try {
                stopWords = readFile("SWE-262/src/stop-words.txt").split(",");
                for (String str : stopWords) {
                    set.add(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


    }
}

