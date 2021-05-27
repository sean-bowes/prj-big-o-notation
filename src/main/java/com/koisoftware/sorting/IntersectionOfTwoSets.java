package com.koisoftware.sorting;

import java.util.*;

public class IntersectionOfTwoSets {
    public List<Integer> findByBruteForce(int[] arr1, int[] arr2) {
        //Time O(NM) Space O(1)
        List<Integer> res = new LinkedList<Integer>();
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    res.add(arr1[i]);
                }
            }
        }
        return res;
    }

    public List<Integer> findBySortTogether(int[] arr1, int[] arr2) {
        //Time O((M+N)log(M+N)) Space O(M+N)
        List<Integer> res = new LinkedList<Integer>();
        int[] all = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, all, 0, arr1.length);
        System.arraycopy(arr2, 0, all, arr1.length, arr2.length);
        Arrays.sort(all);
        for (int i = 0; i < all.length - 1; i++) {
            if (all[i] == all[i + 1]) {
                res.add(all[i]);
                i++;
            }
        }
        return res;
    }

    public List<Integer> findBySortingAndMerge(int[] arr1, int[] arr2) {
        //Time O(MlogM+NlogN) Space O(1)
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        List<Integer> res = new LinkedList<Integer>();
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                res.add(arr1[i]);
                i++;
                j++;
            } else if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr1[i] > arr2[j]) {
                j++;
            }
        }
        return res;
    }

    public List<Integer> findByBinarySearch(int[] arr1, int[] arr2) {
        //Time Min(O(MlogN+NlogN), O(NlogM+MlogM)) Space O(1)
        List<Integer> res = new LinkedList<Integer>();
        if (arr1.length > arr2.length) {
            int[] tmp = arr1;
            arr1 = arr2;
            arr2 = tmp;
        }
        Arrays.sort(arr1);
        for (int i = 0; i < arr2.length; i++) {
            if (binarySearch(arr1, arr2[i])) {
                res.add(arr2[i]);
            }
        }
        return res;
    }

    private boolean binarySearch(int[] arr, int target) {
        int min = 0, max = arr.length - 1;
        while (min <= max) {
            int mid = min + (max - min) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] > target) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    public List<Integer> findByHashmap(int[] nums1, int[] nums2) {
        //Time O(N) Space O(N)
        HashSet<Integer> res = new HashSet<Integer>();
        HashSet<Integer> set = new HashSet<Integer>();
        //set.addAll(Arrays.stream(arr1).boxed().collect(Collectors.toList()));
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                res.add(nums2[i]);
            }
        }
        return new ArrayList<Integer>(res);
        //int[] array
        //return res.stream().mapToInt(i->i).toArray();
    }

    public static void main(String[] args) {
        IntersectionOfTwoSets obj = new IntersectionOfTwoSets();
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println(obj.findByHashmap(nums1, nums2));
        int[] nums3 = {4, 9, 5};
        int[] nums4 = {9, 4, 9, 8, 4};
        System.out.println(obj.findByHashmap(nums3, nums4));
    }
}
