package exercise2;

import java.util.ArrayDeque;
import java.util.Deque;

//Recursion will overflow, So this version is iterate version

public class QuickSort {
    long time=0;
    public void quicksort(String [] nums){
        long start=System.nanoTime();
        sort(nums,0,nums.length-1);

        long end=System.nanoTime();
        time+=end-start;
        System.out.println("The Time of Quick Sort is "+time);
    }
    public void sort(String [] nums, int l, int r){
        Deque<Integer> stack=new ArrayDeque<>();
        if(l<r){
            stack.push(r);
            stack.push(l);
            while(!stack.isEmpty()){
                int left=stack.pop();
                int right=stack.pop();
                int index=divide(left,right,nums);
                if(left<index-1){
                    stack.push(index-1);
                    stack.push(left);
                }
                if(right>index+1){
                    stack.push(right);
                    stack.push(index+1);

                }
            }
        }
    }
    public int  divide(int left, int right, String[] nums){

            //if(left>right) return;

            String pivit=nums[left];
            int l=left,r=right;
            while(l<r){
                while(l<r&&nums[r].compareTo(pivit)>= 0){
                    r--;
                }
                if(l<r){
                    nums[l]=nums[r];
                    l++;
                }
                while(l<r&&nums[l].compareTo(pivit)<0){
                    l++;
                }
                if(l<r) {
                    nums[r]=nums[l];
                    r--;
                }
            }
            nums[l]=pivit;
            return l;
    }
}
