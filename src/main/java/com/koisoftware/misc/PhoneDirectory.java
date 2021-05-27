package com.koisoftware.misc;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class PhoneDirectory {
    private HashSet<Integer> used;
    private Queue<Integer> available;

    /**
     * Initialize your data structure here
     *
     * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
     */
    public PhoneDirectory(int maxNumbers) {
        available = new LinkedList<Integer>();
        used = new HashSet<Integer>();
        for (int i = 0; i < maxNumbers; i++) {
            available.add(i);
        }
    }

    /**
     * Provide a number which is not assigned to anyone.
     *
     * @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
        if (available.isEmpty()) {
            return -1;
        }
        int next = available.poll();
        used.add(next);
        return next;
    }

    /**
     * Check if a number is available or not.
     */
    public boolean check(int number) {
        return !used.contains(number);
    }

    /**
     * Recycle or release a number.
     */
    public void release(int number) {
        if (used.contains(number)) {
            used.remove((Integer) number);
            available.add(number);
        }
    }

    public static void main(String args[]) throws Exception {
        PhoneDirectory obj = new PhoneDirectory(3);
        System.out.println("get=" + obj.get());      // It can return any available phone number. Here we assume it returns 0.
        System.out.println("get=" + obj.get());      // Assume it returns 1.
        System.out.println("get=" + obj.check(2));   // The number 2 is available, so return true.
        System.out.println("get=" + obj.get());      // It returns 2, the only number that is left.
        System.out.println("get=" + obj.check(2));   // The number 2 is no longer available, so return false.
        obj.release(2); // Release number 2 back to the pool.
        System.out.println("get=" + obj.check(2));   // Number 2 is available again, return true.
    }
}
