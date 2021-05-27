package com.koisoftware.median;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public MedianFinder() {
        left = new PriorityQueue<Integer>(Collections.reverseOrder());
        right = new PriorityQueue<Integer>();
    }

    public void addNum(int num) {
        left.add(num);
        right.add(left.poll());
        if (left.size() < right.size()) {
            left.add(right.poll());
        }
    }

    public double findMedian() {
        if (left.size() == right.size()) {
            return (double) (left.peek() + right.peek()) / 2;
        } else {
            return left.peek();
        }
    }

    public static void main(String args[]) throws Exception {
        MedianFinder obj = new MedianFinder();
        double nm;
        obj.addNum(1);
        obj.addNum(2);
        nm = obj.findMedian();
        System.out.println("nm=" + nm);
        obj.addNum(3);
        nm = obj.findMedian();
        System.out.println("nm=" + nm);
    }
}
