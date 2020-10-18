package exercise2;

public class MergeSort {
    long time=0;
    public void mergeSort(String nums[]){
        long start=System.nanoTime();
        sort(nums,0,nums.length-1);
        long end=System.nanoTime();
        time+=end-start;
        System.out.println("The Time of Merge Sort is "+time);
    }

    private static void sort(String[] nums, int l, int r) {
        if(l==r) return ;
        int mid=(r-l)/2+l;
        sort(nums,l,mid);
        sort(nums,mid+1,r);
        merge(nums,l,mid,r );
    }

    private static void merge(String[] nums, int l, int mid, int r) {
        int k=0;
        int p1=l,p2=mid+1;
        //p1. counter of pre part
        //p2. counter of later part
        String[] temp=new String[r-l+1];
        while(p1<=mid&&p2<=r){
            if(nums[p1].compareTo(nums[p2])<=0){
                temp[k]=nums[p1];
                p1++;
                k++;
            }
            else{
                temp[k]=nums[p2];
                p2++;
                k++;
            }
        }
        while(p1<=mid){
            temp[k]=nums[p1];
            k++;
            p1++;
        }
        while(p2<=r){
            temp[k]=nums[p2];
            k++;
            p2++;
        }
        for(int i=0;i<temp.length;i++){
            nums[l+i]=temp[i];
        }
    }
}
