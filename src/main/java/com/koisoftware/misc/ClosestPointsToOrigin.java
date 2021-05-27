package com.koisoftware.misc;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ClosestPointsToOrigin {
    //https://leetcode.com/problems/k-closest-points-to-origin/submissions/

    public int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length == 0 || k == points.length) return points;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new OrderComparator());
        for (int[] point : points) {
            minHeap.offer(point);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        int[][] result = new int[k][2];
        while (k-- > 0) {
            result[k] = minHeap.poll();
        }
        return result;
    }

    private final class OrderComparator implements Comparator<int[]> {
        public int compare(int[] p1, int[] p2) {
            return ((p2[1] * p2[1] + p2[0] * p2[0]) - (p1[1] * p1[1] + p1[0] * p1[0]));
        }
    }

    public void printArray(int arr[][]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i][0] + "," + arr[i][1] + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        ClosestPointsToOrigin obj = new ClosestPointsToOrigin();
        int k = 1;
        int[][] points = {{1, 3}, {-2, 2}};
        int[][] result = obj.kClosest(points, k);
        obj.printArray(result);
        int k2 = 2;
        int[][] points2 = {{3, 3}, {5, -1}, {-2, 4}};
        int[][] result2 = obj.kClosest(points2, k2);
        obj.printArray(result2);
    }
}
