package com.koisoftware.misc;

public class IsPowerOfTwo {

    public boolean isPowerOfTwo(int num) {
        //Time Complexity: O(log4n)
        //Space Complexity: O(1)
        while (num > 0) {
            if (num == 1) {
                return true;
            }
            if (num % 2 != 0) {
                return false;
            } else {
                num /= 2;
            }
        }
        return false;
    }

    private double logn(int n, int r) {
        return Math.log(n) / Math.log(r);
    }

    public boolean isPowerOfTwoByMathLog(int n) {
        //Time Complexity: O(log4n)
        //Space Complexity: O(1)
        if (n == 0) {
            return false;
        }
        return Math.floor(logn(n, 2)) == Math.ceil(logn(n, 2));
    }

    public boolean isPowerOfTwoByBitPattern(int n) {
        //There is only one bit set in the binary representation
        //The bits don’t AND(&) any part of the pattern 0xAAAAAAAA
        //For example: 16 (10000) is power of 4 because there is only one bit set and 0x10 & 0xAAAAAAAA is zero.
        //Time Complexity: O(log4n)
        //Space Complexity: O(1)
        return n != 0 && ((n & (n - 1)) == 0);
    }

    public static void main(String args[]) {
        IsPowerOfTwo obj = new IsPowerOfTwo();
        System.out.println(obj.isPowerOfTwo(64)); //true
        System.out.println(obj.isPowerOfTwo(20)); //false
        System.out.println(obj.isPowerOfTwo(16)); //true
        System.out.println(obj.isPowerOfTwoByMathLog(16)); //true
        System.out.println(obj.isPowerOfTwoByBitPattern(16)); //true
        System.out.println(obj.isPowerOfTwoByBitPattern(64)); //true
    }
}
