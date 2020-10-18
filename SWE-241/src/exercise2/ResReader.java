package exercise2;

import java.io.*;

public class ResReader {
    public static void main(String[] args) throws IOException {
        String source="SWE-241/src/exercise1/pride-and-prejudice.txt";
        String data=readFromFile(source).trim();
        String strs[]=data.split("[ -.:/@]");
        for(String str:strs){
            str.replaceAll("[^a-zA-Z0-9]]","");
        }


        String strs2[]=data.split("[ -.:/@]");
        for(String str:strs2){
            str.replaceAll("[^a-zA-Z0-9]]","");
        }
        String strs3[]=data.split("[ -.:/@]");
        for(String str:strs3){
            str.replaceAll("[^a-zA-Z0-9]]","");
        }
        String strs4[]=data.split("[ -.:/@]");
        for(String str:strs4){
            str.replaceAll("[^a-zA-Z0-9]]","");
        }
        String strs5[]=data.split("[ -.:/@]");
        for(String str:strs5){
            str.replaceAll("[^a-zA-Z0-9]]","");
        }





        //InsertionSort

        InsertionSort insertionSort=new InsertionSort();
        insertionSort.insertionSort(strs);
        writeToFile("SWE-241/src/exercise2/insertionsort.txt", insertionSort.time);




        //SelectionSort

        SelectionSort selectionSort=new SelectionSort();
        selectionSort.selectionSort(strs2);
        writeToFile("SWE-241/src/exercise2/selectionsort.txt", selectionSort.time);
//


        //QuickSort
        QuickSort quickSort=new QuickSort();
        quickSort.quicksort(strs3);
        writeToFile("SWE-241/src/exercise2/quicksort.txt", quickSort.time);


        //mergesort
        MergeSort mergeSort=new MergeSort();
        mergeSort.mergeSort(strs4);
        writeToFile("SWE-241/src/exercise2/mergesort.txt", mergeSort.time);


        //heapSort
        HeapSort heapSort=new HeapSort();
        heapSort.heapSort(strs5);
        writeToFile("SWE-241/src/exercise2/heapsort.txt", heapSort.time);



//        for(int i=0;i<strs.length;i++){
//            System.out.println(strs[i]);
//        }
        //System.out.println(quickSort.time);
    }
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
    public static  void writeToFile(String source,long data) throws IOException {
        File file=new File(source);
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
        bw.write(String.valueOf(data));
        bw.write("\r\n");
        bw.close();
    }
}
