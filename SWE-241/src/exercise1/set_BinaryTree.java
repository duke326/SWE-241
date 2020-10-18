package exercise1;

import java.io.IOException;

public class set_BinaryTree {
    //TreeSet set=new TreeSet();
    myBinaryTree root;
    long time=0;
    long searchTime=0;
    public  set_BinaryTree(){
        root=new myBinaryTree();
        //time=0;
    }
    boolean add(String str) throws IOException {
        if(!root.contains(str)){
            long start=System.nanoTime();
            boolean res=root.add(str);
            long end=System.nanoTime();
            time+=end-start;
           // reader.writeToFile("E:\\java project\\UCI algo\\UCI algo\\src\\exercise1\\add_bt.txt", end-start);
            return res;
        }
        return false;

    }
    int size(){
        return root.size();
    }

    boolean contains(String str) throws IOException {
        long start=System.nanoTime();
        boolean res= root.contains(str);
        long end=System.nanoTime();
        searchTime+=end-start;
        //reader.writeToFile("E:\\java project\\UCI algo\\UCI algo\\src\\exercise1\\search_bt.txt", end-start);
        return res;
    }
//    public static void main(String[] args) {
//        set_BinaryTree set=new set_BinaryTree();
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
class myBinaryTree{
    myTreeNode root;
    int size;
    public myBinaryTree(){
        root=null;
        size=0;
    }
    //add
    public boolean  add(String str){
        myTreeNode temp=new myTreeNode(str);
        if(this.root==null){
            this.root=temp;
            this.size++;
            return true;

        }
        else{
            myTreeNode node=this.root;
            while(true){
                if(node.str.compareTo(str)>=0){
                    if(node.left==null){
                        node.left=temp;
                        size++;
                        return true;
                    }
                    else{
                        node=node.left;
                    }
                }
                else if (node.str.compareTo(str)<0){
                    if(node.right==null){
                        node.right=temp;
                        size++;
                        return true;
                    }
                    else{
                        node=node.right;
                    }
                }
            }
        }
        //return false;
    }

    //size
    public int size(){
        return this.size;

    }


    //contains


    public boolean contains( String str){
        if(this.root==null) return false;
        else if (this.root.str.equals(str)) return true;
        else{
            myTreeNode node=this.root;
            while (node.left!=null||node.right!=null){
                if(node.str.compareTo(str)>=0){
                    if(node.left==null){
                        return false;
                    }
                    else if(node.left.str.equals(str)){
                        return true;
                    }
                    else{
                        node=node.left;
                    }
                }
                else if (node.str.compareTo(str)<0){
                    if(node.right==null){
                        return false;
                    }
                    else if (node.right.str.equals(str)){
                        return true;
                    }
                    else{
                        node=node.right;
                    }
                }
            }
        }
    return false;
    }
}
class myTreeNode{
    myTreeNode left;
    myTreeNode right;
    String str;
    public myTreeNode(String str){
        this.str=str;
    }
}
