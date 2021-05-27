package com.koisoftware.misc;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
    public int[] printMax(int arr[], int k) {
        //Given an array and an integer K, find the maximum for each and every contiguous subarray of size k.
        //Time Complexity: O(n).
        //Auxiliary Space: O(k).
        ArrayList<Integer> returnList = new ArrayList<>();
        Deque<Integer> doubleEndedQueue = new LinkedList<Integer>();
        int i;
        for (i = 0; i < k; ++i) {
            while (!doubleEndedQueue.isEmpty() && arr[i] >= arr[doubleEndedQueue.peekLast()]) {
                doubleEndedQueue.removeLast();
            }
            doubleEndedQueue.addLast(i);
        }
        for (; i < arr.length; ++i) {
            returnList.add(arr[doubleEndedQueue.peek()]);
            while ((!doubleEndedQueue.isEmpty()) && doubleEndedQueue.peek() <= i - k) {
                doubleEndedQueue.removeFirst();
            }
            while ((!doubleEndedQueue.isEmpty()) && arr[i] >= arr[doubleEndedQueue.peekLast()]) {
                doubleEndedQueue.removeLast();
            }
            doubleEndedQueue.addLast(i);
        }
        returnList.add(arr[doubleEndedQueue.peek()]);
        int[] returnArray = returnList.stream().mapToInt(h -> h).toArray();
        return returnArray;
    }

    public void printArrayInteger(int nm[]) {
        for (int i = 0; i < nm.length; i++) {
            System.out.print(nm[i] + " ");
        }
    }

    public static void main(String[] args) {
        SlidingWindowMaximum obj = new SlidingWindowMaximum();
        int arr[] = {12, 1, 78, 90, 57, 89, 56};
        int k = 3;
        System.out.print("Solution1=");
        obj.printArrayInteger(obj.printMax(arr, k));
    }
}
