package com.koisoftware.misc;

public class MaxSubarraySumCircular {
    public int maxSubarraySumCircular(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int S = 0;  // S = sum(nums)
        for (int x : nums) {
            S += x;
        }
        int ans1 = signVariant(nums, 0, nums.length - 1, 1);
        int ans2 = S + signVariant(nums, 1, nums.length - 1, -1);
        int ans3 = S + signVariant(nums, 0, nums.length - 2, -1);
        return Math.max(ans1, Math.max(ans2, ans3));
    }

    public int signVariant(int[] A, int i, int j, int sign) {
        //Kadane's algorithm
        int ans = Integer.MIN_VALUE;
        int cur = Integer.MIN_VALUE;
        for (int k = i; k <= j; ++k) {
            cur = sign * A[k] + Math.max(cur, 0);
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    private void printArray(int arr[]) {
        int i;
        for (i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        MaxSubarraySumCircular obj = new MaxSubarraySumCircular();
        int[] nums0 = {1, -2, 3, -2};
        obj.printArray(nums0);
        System.out.println(obj.maxSubarraySumCircular(nums0)); //3
        int[] nums1 = {5, -3, 5};
        obj.printArray(nums1);
        System.out.println(obj.maxSubarraySumCircular(nums1)); //10
        int[] nums2 = {3, -1, 2, -1};
        obj.printArray(nums2);
        System.out.println(obj.maxSubarraySumCircular(nums2)); //4
        int[] nums3 = {3, -2, 2, -3};
        obj.printArray(nums3);
        System.out.println(obj.maxSubarraySumCircular(nums3)); //3
        int[] nums4 = {-2, -3, -1};
        obj.printArray(nums4);
        System.out.println(obj.maxSubarraySumCircular(nums4)); //-1
    }
}