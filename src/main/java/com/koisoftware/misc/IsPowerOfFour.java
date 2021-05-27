package com.koisoftware.misc;

import java.util.ArrayList;
import java.util.List;

public class IsPowerOfFour {

    public IsPowerOfFour() {
        preCompute();
    }

    public List<Integer> nums = new ArrayList();

    public void preCompute() {
        int n = 15;
        int lastNum = 1;
        nums.add(lastNum);
        for (int i = 1; i < n + 1; ++i) {
            lastNum = lastNum * 4;
            nums.add(lastNum);
        }
    }

    public boolean isPowerOfFourByPreCompute(int n) {
        return nums.contains(n);
    }

    public boolean isPowerOfFour(int num) {
        //Time Complexity: O(log4n)
        //Space Complexity: O(1)
        while (num > 0) {
            if (num == 1) {
                return true;
            }
            if (num % 4 != 0) {
                return false;
            } else {
                num /= 4;
            }
        }
        return false;
    }

    private double logn(int n, int r) {
        return Math.log(n) / Math.log(r);
    }

    public boolean isPowerOfFourByMathLog(int n) {
        //Time Complexity: O(log4n)
        //Space Complexity: O(1)
        if (n == 0) {
            return false;
        }
        return Math.floor(logn(n, 4)) == Math.ceil(logn(n, 4));
    }

    public boolean isPowerOfFourByBitPattern(int n) {
        //There is only one bit set in the binary representation
        //The bits don’t AND(&) any part of the pattern 0xAAAAAAAA
        //For example: 16 (10000) is power of 4 because there is only one bit set and 0x10 & 0xAAAAAAAA is zero.
        //Time Complexity: O(log4n)
        //Space Complexity: O(1)
        return n != 0 && ((n & (n - 1)) == 0) && (n & 0xAAAAAAAA) == 0;
    }

    public static void main(String args[]) {
        IsPowerOfFour obj = new IsPowerOfFour();
        System.out.println(obj.isPowerOfFour(20)); //false
        System.out.println(obj.isPowerOfFour(16)); //true
        System.out.println(obj.isPowerOfFourByMathLog(16)); //true
        System.out.println(obj.isPowerOfFourByBitPattern(13)); //false
        System.out.println(obj.isPowerOfFourByBitPattern(16)); //true
    }
}
