package com.koisoftware.misc;

import java.util.*;
import java.util.stream.Collectors;

public class FindKClosestElements {
    //https://leetcode.com/problems/find-k-closest-elements/

    public List<Integer> findClosestElementsByList(int[] arr, int k, int x) {
        List<Integer> ans = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.sort(ans, (a, b) -> a == b ? a - b : Math.abs(a - x) - Math.abs(b - x));
        ans = ans.subList(0, k);
        Collections.sort(ans);
        return ans;
    }

    public List<Integer> findClosestElementsByMinHeap(int[] arr, int k, int x) {
        //create min heap; max heap does not work for this question
        //Min-Heap − Where the value of the root node is less than or equal to either of its children.
        //Max-Heap − Where the value of the root node is greater than or equal to either of its children.
        //Both trees are constructed using the same input and order of arrival.
        //time complexity: O(nlogn)
        //space complexity: O(n)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        for (int i : arr) {
            int[] array = new int[2];
            array[0] = i;
            array[1] = Math.abs(x - i);
            pq.add(array);
        }
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < k; i++) {
            res.add(pq.poll()[0]);
        }
        Collections.sort(res);
        return res;
    }

    public static void main(String args[]) {
        FindKClosestElements obj = new FindKClosestElements();
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4, x = 3;
        System.out.println("1 ByList=" + obj.findClosestElementsByList(arr, k, x));
        System.out.println("1 ByMinHeap=" + obj.findClosestElementsByMinHeap(arr, k, x));
        int[] arr2 = {1, 2, 3, 4, 5};
        int k2 = 4, x2 = -1;
        System.out.println("2 ByList=" + obj.findClosestElementsByList(arr2, k2, x2));
        System.out.println("2 ByMinHeap=" + obj.findClosestElementsByMinHeap(arr2, k2, x2));
    }
}
