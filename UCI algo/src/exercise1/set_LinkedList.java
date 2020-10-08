package exercise1;

import java.io.IOException;

public class set_LinkedList {
    myLinkedList list;
    long time=0;
    long searchTime=0;
    //List<String > list1=new exercise1.LinkedList<>();

    public  set_LinkedList(){
        list=new myLinkedList();
    }
    public boolean add(String word) throws IOException {
        if(!list.contains(word)){
            long start=System.nanoTime();
            list.add(word);
            Long end=System.nanoTime();
            time+=end-start;
            //list1.size();
            reader.writeToFile("E:\\java project\\UCI algo\\UCI algo\\src\\exercise1\\add_ll.txt", end-start);
            return true;
        }
        return false;
    }
    public boolean contains(String word) throws IOException {
        long start=System.nanoTime();
        boolean res= list.contains(word);
        long end=System.nanoTime();
        searchTime+=end-start;
        reader.writeToFile("E:\\java project\\UCI algo\\UCI algo\\src\\exercise1\\search_ll.txt", end-start);
        return res;
    }
    public int  size(){
        return list.size();
    }
    public void insertTime(String word) throws IOException {

        if(!contains(word)){
            long start=System.nanoTime();
            add(word);
            long end=System.nanoTime();
            time=end-start;
        }
        //return time;
    }

//    public static void main(String[] args) {
//        set_LinkedList set=new set_LinkedList();
//        set.add("1");
//        set.add("2");
//        set.add("3");
//        set.add("4");
//        System.out.println(set.contains("5"));
//
//        System.out.println(set.add("1"));
//        System.out.println(set.add("2"));
//        System.out.println(set.add("5"));
//        System.out.println(set.contains("5"));
//        System.out.println(set.size());
//    }
}


class myLinkedList{
    myListNode head;
    public myLinkedList(){
        head=null;
    }
    public boolean add(String str){
        myListNode temp=new myListNode(str);
        myListNode curr=head;
        if(head==null){
            head=temp;
        }
        else{
            while(curr.next!=null){
                if(!curr.str.equals(str)){
                    curr=curr.next;
                }
                else{
                    return false;
                }
            }
            curr.next=temp;
        }
        return true;
    }
    public boolean contains(String str){
        myListNode curr=head;
        while(curr!=null){
            if(curr.str.equals(str)){
                return true;
            }
            curr=curr.next;
        }
        return false;
    }
    public int size(){
        int count=0;
        myListNode curr=head;
        while(curr!=null){
            count++;
            curr=curr.next;
        }
        return count;
    }

}
class myListNode{
    String str;
    myListNode next;
    public  myListNode(String str){
        this.str=str;
    }
}
