package com.koisoftware.sorting;

import com.koisoftware.exceptions.EmptyHeapException;

import java.util.Comparator;

public class HeapSort {
    public static <T> T[] heapSort(T[] array, Comparator<T> comparator) {
        Heap<T> heap = new SimpleHeapImpl<T>(array, comparator);
        for (int index = array.length - 1; !heap.isEmpty(); index--) {
            try {
                array[index] = heap.pop();
            } catch (EmptyHeapException e) {
                // This should never occur
                throw new RuntimeException(e);
            }
        }
        return array;
    }
}
