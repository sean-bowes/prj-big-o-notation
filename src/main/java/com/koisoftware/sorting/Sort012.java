package com.koisoftware.sorting;

/**
 * Sort an array containing only 0s and/or 1s and/or 2s.
 */
public class Sort012 {

    public void sortColors(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        int mid = 0, temp = 0;
        while (mid <= hi) {
            switch (nums[mid]) {
                case 0: {
                    temp = nums[lo];
                    nums[lo] = nums[mid];
                    nums[mid] = temp;
                    lo++;
                    mid++;
                    break;
                }
                case 1:
                    mid++;
                    break;
                case 2: {
                    temp = nums[mid];
                    nums[mid] = nums[hi];
                    nums[hi] = temp;
                    hi--;
                    break;
                }
            }
        }
    }

    private void printArray(int arr[]) {
        int i;
        for (i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Sort012 obj = new Sort012();
        int arr[] = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        obj.printArray(arr);
        obj.sortColors(arr);
        obj.printArray(arr);
    }
}
