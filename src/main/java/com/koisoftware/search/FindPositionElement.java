package com.koisoftware.search;

public class FindPositionElement {
    public static void main(String[] args) {
        //Leetcode 34. Find First and Last Position of Element in Sorted Array
        //Input: nums = [5,7,7,8,8,10], target = 8
        //Output: [3,4]
        int[] nm = {5, 7, 7, 8, 8, 10};
        int[] tt = searchRange(nm, 8);
        System.out.println("searchRange=[" + tt[0] + "," + tt[1] + "]");
    }

    public static int[] searchRange(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        int first = l;
        if (l < nums.length && nums[l] == target) {//l is in boundary and is the target
            l = 0;
            r = nums.length - 1;
            while (l < r) {
                int m = l + (r - l + 1) / 2;
                if (nums[m] > target) {
                    r = m - 1;
                } else {
                    l = m;
                }
            }

            return new int[]{first, r};
        }

        return new int[]{-1, -1};
    }
}
