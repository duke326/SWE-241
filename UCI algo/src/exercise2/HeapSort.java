package exercise2;

public class HeapSort {
    long time=0;
    public void heapSort(String [] strs){
        long start=System.nanoTime();
        build(strs);
        for(int i=strs.length-1;i>=0;i--){
            SelectionSort.swap(strs,i,0);
            heapify(strs,i,0);
        }
        long end=System.nanoTime();
        time+=end-start;
        System.out.println("The Time of Heap Sort is "+time);
    }

    private void build(String[] strs) {
        for(int i= strs.length/2;i>=0;i--){
            heapify(strs, strs.length,i);
        }
    }

    private void heapify(String [] strs, int size, int i ) {
        int left=2*i+1;
        int right=2*i+2;
        int max=i;
        if(left<size&&strs[left].compareTo(strs[max])>=0){
            max=left;
        }
        if (right<size&&strs[right].compareTo(strs[max])>=0){
            max=right;
        }
        if(max!=i){
            SelectionSort.swap(strs, max,i);
            heapify(strs,size,max);

        }
    }
}
