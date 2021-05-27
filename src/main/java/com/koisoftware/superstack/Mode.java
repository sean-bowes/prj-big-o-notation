package com.koisoftware.superstack;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;

public class Mode {
    private Map<Integer, Integer> valueCount;
    private Map<Integer, LinkedHashSet<Integer>> countList;
    private int max;

    public Mode() {
        valueCount = new HashMap<>();
        countList = new HashMap<>();
        max = 0;
    }

    public void put(int val) {
        if (valueCount.containsKey(val)) {
            int count = valueCount.get(val);
            valueCount.put(val, count + 1);
            countList.get(count).remove(val);
            if (countList.get(count).isEmpty()) countList.remove(count);
            countList.computeIfAbsent(count + 1, x -> new LinkedHashSet<>())
                    .add(val);
            if (max == count) max++;
        } else {
            valueCount.put(val, 1);
            if (max == 0) max++;
            countList.computeIfAbsent(1, x -> new LinkedHashSet<>()).add(val);
        }
    }

    public void remove(int val) {
        if (valueCount.containsKey(val)) {
            int count = valueCount.get(val);
            if (count - 1 == 0) {
                valueCount.remove(val);
            } else {
                valueCount.put(val, count - 1);
            }

            countList.get(count).remove(val);
            if (count == max && countList.get(count).isEmpty()) max--;
            if (countList.get(count).isEmpty()) countList.remove(count);
            countList.computeIfAbsent(count - 1, x -> new LinkedHashSet<>()).add(val);
        }
    }

    /**
     * @return oldest most frequent element in the stack
     * @throws Exception
     */
    public int getMostFrequentValue() throws Exception {
        if (valueCount.isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return countList.get(max).iterator().next();
    }
}
