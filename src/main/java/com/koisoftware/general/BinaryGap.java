package com.koisoftware.general;

/**
 * A binary gap within a positive integer N is any maximal sequence of
 * consecutive zeros that is surrounded by ones at both ends in the binary
 * representation of N.
 * <p>
 * For example, number 9 has binary representation 1001 and contains a binary
 * gap of length 2. The number 529 has binary representation 1000010001 and
 * contains two binary gaps: one of length 4 and one of length 3. The number 20
 * has binary representation 10100 and contains one binary gap of length 1. The
 * number 15 has binary representation 1111 and has no binary gaps.
 * <p>
 * Write a function that, given a positive integer N, returns the length of its
 * longest binary gap. The function should return 0 if N doesn't contain a
 * binary gap. For example, given N = 1041 the function should return 5, because
 * N has binary representation 10000010001 and so its longest binary gap is of
 * length 5. Assume that: N is an integer within the range [1..2,147,483,647].
 */
public class BinaryGap {
    public int binaryGap(int number) {
        while (number != 0 && (number & 1) == 0) {
            number >>>= 1;
        }

        if (number == 0) {
            return 0;
        }

        int count = 0;
        int max = Integer.MIN_VALUE;
        while (number != 0) {
            if ((number & 1) == 0) {
                count++;
            } else {
                if (count > max) {
                    max = count;
                }
                count = 0;
            }

            number >>>= 1;
        }

        return count;
    }
}
