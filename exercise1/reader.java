package exercise1;

import java.io.*;

public class reader {
    //final test
    public static String readFromFile(String source) throws IOException {
        File file = new File(source);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String temp = null;
        StringBuffer sb = new StringBuffer();
        //temp = br.readLine();
        while ((temp =br.readLine())!= null) {
            sb.append(temp + " ");
            //temp = br.readLine();
        }
        return sb.toString().trim();
    }

    public  static void writeToFile(String source, long data) throws IOException {
        File file=new File(source);
        BufferedWriter br=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
        br.write(String.valueOf(data));
        br.write("\r\n");
        br.close();
    }

    public static void main(String[] args) throws IOException {
        String str = null;
        String str2=null;
        exercise1.set_HashTable setHashTable=new exercise1.set_HashTable(8);
        exercise1.set_LinkedList setLinkedList=new exercise1.set_LinkedList();
        exercise1.set_BinaryTree setBinaryTree=new exercise1.set_BinaryTree();
        try {
            str = readFromFile("SWE-241/src/exercise1/pride-and-prejudice.txt").trim();
            str2= readFromFile("SWE-241/src/exercise1/words-shuffled.txt").trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // might the causes of diviation with answer 7104
        String line[] = str.split("[ -.:/@]");
        String line2[] = str2.split("[ -.:/@]");
        for (String strTemp : line) {
            String temp=strTemp.replaceAll("[^A-Za-z\\d+]", "");
            if(!temp.equals("")){
                setLinkedList.add(temp);
                //setLinkedList.insertTime(temp);
                setHashTable.add(temp);
                setBinaryTree.add(temp);
                //setHashTable.insertTime(temp);
            }

        }
        int count=0;
        for(String strTemp:line2){
            String temp=strTemp.replaceAll("[^A-Za-z\\d+]", "");
            if(!temp.equals("")&&!(setLinkedList.contains(temp)
                    &&setBinaryTree.contains(temp)
                    &&setHashTable.contains(temp))){
                count++;
                System.out.println(temp);
            }


        }
        System.out.println("\n"+"/////////////LinkedList///////////////");
        System.out.println("the size of Set implemented by exercise1.LinkedList is " +setLinkedList.size());
        System.out.println("the total runtime of Set implemented by exercise1.LinkedList is "+setLinkedList.time);
        System.out.println("the total search time of Set implemented by exercise1.LinkedList is "+setLinkedList.searchTime);
        System.out.println("\n"+"///////////////////HashTable////////////////////");
        System.out.println("the size of Set implemented by HashTable is " +setHashTable.size());
        System.out.println("the total runtime of Set implemented by HashTable is "+setHashTable.time);
        System.out.println("the total search time of Set implemented by HashTable is "+setHashTable.searchTime);
        System.out.println("\n"+"////////////////BinaryTree///////////////////");
        System.out.println("the size of Set implemented by BinaryTree is " +setBinaryTree.size());
        System.out.println("the total runtime of Set implemented by BinaryTree is "+setBinaryTree.time);
        System.out.println("the total search time of Set implemented by BinaryTree is "+setBinaryTree.searchTime);

        //doesn't contain
        System.out.println("\n"+"the number which set doesn't contain is "+count);
    }
}
