package com.koisoftware.sorting;

import org.junit.jupiter.api.Test;

public class QuickSortRaw {

    @Test
    public void quickSortByArray() {
        int[] arr = {4, 5, 1, 2, 3, 3};
        QuickSort.quickSort(arr, 0, arr.length - 1);
        //System.out.println(java.util.Arrays.toString(arr));
    }

}
