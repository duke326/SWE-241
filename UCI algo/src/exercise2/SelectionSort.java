package exercise2;

public class SelectionSort {
    long time=0;
    public void selectionSort(String [] nums){
        long start=System.nanoTime();
        for(int i=0;i<nums.length;i++){
            int min=i;
            for(int j=i+1;j<nums.length;j++){
                if(nums[j].compareTo(nums[min])<0) min=j;
            }
           if(min!=i) swap(nums,min,i);
        }
        long end=System.nanoTime();
        time+=end-start;
        System.out.println("The Time of Selection Sort is "+time);
    }
    public static void swap(String nums[], int i,int j){
        String temp="";
        temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}
