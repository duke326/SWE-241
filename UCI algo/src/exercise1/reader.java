package exercise1;

import java.io.*;

public class reader {
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

    public static void main(String[] args) {
        String str = null;
        String str2=null;
        set_HashTable setHashTable=new set_HashTable(8);
        set_LinkedList setLinkedList=new set_LinkedList();
        set_BinaryTree setBinaryTree=new set_BinaryTree();
        try {
            str = readFromFile("E:/pride-and-prejudice.txt").trim();
            str2= readFromFile("E:/words-shuffled.txt").trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // might the causes of diviation with answer 7104
        String line[] = str.split("[;,?| .!:#\\[\\]*\\\"-]");
        String line2[] = str2.split("[;,?| .!:#\\[\\]*\\\"-]");
        for (String temp : line) {
            if(!temp.equals("")){
                setLinkedList.add(temp);
                //setLinkedList.insertTime(temp);
                setHashTable.add(temp);
                setBinaryTree.add(temp);
                //setHashTable.insertTime(temp);
            }

        }
        int count=0;
        for(String temp:line2){
            if(!temp.equals("")&&!(setLinkedList.contains(temp)
                    ||setBinaryTree.contains(temp)
                    ||setHashTable.contains(temp))){
                count++;
            }


        }
        //linkedList
        System.out.println("the size of Set implemented by exercise1.LinkedList is " +setLinkedList.size());
        System.out.println("the total runtime of Set implemented by exercise1.LinkedList is "+setLinkedList.time);
        System.out.println("the total search time of Set implemented by exercise1.LinkedList is "+setLinkedList.searchTime);
        //hashtable
        System.out.println("the size of Set implemented by HashTable is " +setHashTable.size());
        System.out.println("the total runtime of Set implemented by HashTable is "+setHashTable.time);
        System.out.println("the total search time of Set implemented by HashTable is "+setHashTable.searchTime);
        //binaryTree
        System.out.println("the size of Set implemented by BinaryTree is " +setBinaryTree.size());
        System.out.println("the total runtime of Set implemented by BinaryTree is "+setBinaryTree.time);
        System.out.println("the total search time of Set implemented by BinaryTree is "+setBinaryTree.searchTime);

        //doesn't contain
        System.out.println("the number which set doesn't contain is "+count);
    }
}
