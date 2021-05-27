package com.koisoftware.search;

public class CheckDuplicatesWithBitArray {
    int[] arr;

    public CheckDuplicatesWithBitArray(int n) {
        // Divide by 32. To store n bits, we need
        // n/32 + 1 integers (Assuming int is stored
        // using 32 bits)
        arr = new int[(n >> 5) + 1];
    }

    // Get value of a bit at given position
    boolean get(int pos) {
        // Divide by 32 to find position of
        // integer.
        int index = (pos >> 5);
        // Now find bit number in arr[index]
        int bitNo = (pos & 0x1F);
        // Find value of given bit number in
        // arr[index]
        return (arr[index] & (1 << bitNo)) != 0;
    }

    // Sets a bit at given position
    void set(int pos) {
        // Find index of bit position
        int index = (pos >> 5);
        // Set bit number in arr[index]
        int bitNo = (pos & 0x1F);
        arr[index] |= (1 << bitNo);
    }

    static void checkDuplicates(int[] arr) {
        CheckDuplicatesWithBitArray ba = new CheckDuplicatesWithBitArray(320000);
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (ba.get(num)) {
                System.out.print(num + " ");
            } else {
                ba.set(num);
            }
        }
    }

    public static void main(String[] args) throws java.lang.Exception {
        int[] arr = {1, 5, 1, 10, 12, 10};
        checkDuplicates(arr);
    }
}
