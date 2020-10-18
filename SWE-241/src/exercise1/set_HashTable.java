package exercise1;

import java.io.IOException;

public class set_HashTable {
    myHashTable table;
    long time=0;
    long searchTime=0;
    public set_HashTable(int size){
        table=new myHashTable(size);
    }
    public boolean add(String str) throws IOException {
        int hash=table.hash(str);
        myLinkedList temp=table.list[hash];
        if (temp.contains(str)) {
            return false;
        }
        else{
            long start=System.nanoTime();
            temp.add(str);
            Long end=System.nanoTime();
            time+=end-start;
            //reader.writeToFile("E:\\java project\\UCI algo\\UCI algo\\src\\exercise1\\add_ht.txt", end-start);
        }
        return true;
    }
    public boolean contains(String str) throws IOException {
        long start=System.nanoTime();
        int hash=table.hash(str);
        myLinkedList temp=table.list[hash];
        boolean res=temp.contains(str);
        long end=System.nanoTime();
        searchTime+=end-start;
        //reader.writeToFile("E:\\java project\\UCI algo\\UCI algo\\src\\exercise1\\search_ht.txt", end-start);
        return res;
    }
    public int size(){
        int size=0;
        for(int i=0;i<table.size;i++){
            myLinkedList temp=table.list[i];
            size+=temp.size();

        }
        return size;
    }
    public void insertTime(String word) throws IOException {

        if(!contains(word)){
            long start=System.nanoTime();
            add(word);
            long end=System.nanoTime();
            time+=end-start;
        }
        //return time;
    }

//    public static void main(String[] args) {
//        set_HashTable set=new set_HashTable(10);
//        set.add("1");
//        set.add("2");
//        set.add("3");
//        set.add("4");
//        System.out.println(set.contains("1"));
//
//        System.out.println(set.add("1"));
//        System.out.println(set.add("2"));
//        System.out.println(set.add("5"));
//        System.out.println(set.size());
//    }
}
class myHashTable{
    int size;
    myLinkedList [] list;
    public myHashTable(int size){
        this.size=size;
        list=new myLinkedList[size];
        for(int i=0;i<size;i++){
            list[i]=new myLinkedList();
        }
    }
    public int hash(String str){
        char chs[]=str.toCharArray();
        int [] val=new int [chs.length];
        for(int i=0;i<chs.length;i++){
            val[i]=chs[i]-0;
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<val.length;i++){
            sb.append(val[i]);
        }
        int res=0;
        if(sb.length()>=12){
            res=1;
        }
        else{
            res=(int)Long.parseLong(sb.toString())%size;
        }
        //int res=
        //int  res2=P.parseLong(sb.toString())%size;
        if (res<0) res=1;

        return res;
    }


}
