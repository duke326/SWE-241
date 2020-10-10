package exercise2;

public class InsertionSort {
    long time=0;
    public void insertionSort(String nums[]){
        long start=System.nanoTime();
        for(int i=1;i<nums.length;i++){
            int j=i;
            while(j>0){
                if(nums[j].compareTo(nums[j-1])<=0){
                    SelectionSort.swap(nums,j,j-1);
                    j--;
                }
                else{
                    break;
                }
            }
        }

        long end=System.nanoTime();
        time+=end-start;
        System.out.println("The Time of Insertion Sort is "+time);
    }
}
