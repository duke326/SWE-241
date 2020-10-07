package exercise2;

public class QuickSort {
    public static void main(String[] args) {
        int nums[]=new int []{-1,1,2,3,50,6,124,39,54,34,67,34,1};
        QuickSort qs=new QuickSort();
        qs.quicksort(0,nums.length-1,nums);
        for(int i:nums){
            System.out.println(i);
        }
    }
    public void quicksort(int left, int right, int[] nums){
            if(left>right) return;

            int pivit=nums[left],l=left,r=right;
            while(l<r){
                while(l<r&&nums[r]>= pivit){
                    r--;
                }
                if(l<r){
                    nums[l]=nums[r];
                    l++;
                }
                while(l<r&&nums[l]<pivit){
                    l++;
                }
                if(l<r) {
                    nums[r]=nums[l];
                    r--;
                }
            }
            nums[l]=pivit;
            quicksort(left,l-1,nums);
            quicksort(l+1,right,nums);

    }
}
