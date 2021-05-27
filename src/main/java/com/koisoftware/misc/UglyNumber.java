package com.koisoftware.misc;

import java.util.TreeSet;

public class UglyNumber {
    private int getNthUglyNo(int n) {
        // To store ugly numbers
        int ugly[] = new int[n];
        int i2 = 0, i3 = 0, i5 = 0;
        int next_multiple_of_2 = 2;
        int next_multiple_of_3 = 3;
        int next_multiple_of_5 = 5;
        int next_ugly_no = 1;

        ugly[0] = 1;

        for (int i = 1; i < n; i++) {
            next_ugly_no = Math.min(next_multiple_of_2, Math.min(next_multiple_of_3, next_multiple_of_5));

            ugly[i] = next_ugly_no;
            if (next_ugly_no == next_multiple_of_2) {
                i2 = i2 + 1;
                next_multiple_of_2 = ugly[i2] * 2;
            }
            if (next_ugly_no == next_multiple_of_3) {
                i3 = i3 + 1;
                next_multiple_of_3 = ugly[i3] * 3;
            }
            if (next_ugly_no == next_multiple_of_5) {
                i5 = i5 + 1;
                next_multiple_of_5 = ugly[i5] * 5;
            }
        }

        // End of for loop (i=1; i<n; i++)
        return next_ugly_no;
    }

    private long nthUglyNumber(int n) {
        TreeSet<Long> t = new TreeSet<>();
        // Ugly number sequence starts with 1
        t.add(1L);
        int i = 1;
        // when i==n we have the nth ugly number
        while (i < n) {
            // remove the ith ugly number and add back its
            // multiples of 2,3 and 5 back to the set
            long temp = t.pollFirst();
            t.add(temp * 2);
            t.add(temp * 3);
            t.add(temp * 5);
            i++;
            // the first element of set is always the ith ugly number
        }
        return t.pollFirst();
    }

    // Driver code
    public static void main(String args[]) {
        int n = 150;
        UglyNumber obj = new UglyNumber();
        System.out.println(obj.getNthUglyNo(n));
        System.out.println(obj.nthUglyNumber(n));
        n = 4;
        System.out.println(obj.nthUglyNumber(n));
    }
}
