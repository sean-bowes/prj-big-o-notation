package com.koisoftware.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCacheLinkedHashSet {

    // support get, put with frequency count
    Map<Integer, Integer> map;
    Map<Integer, Integer> keyCountMap;
    Map<Integer, LinkedHashSet<Integer>> countToKeysMap;
    int capacity;
    int min;

    public LFUCacheLinkedHashSet(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.keyCountMap = new HashMap<>();
        this.countToKeysMap = new HashMap<>();
        this.min = 1;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        int val = map.get(key);
        int oldCount = keyCountMap.get(key);
        int newCount = oldCount + 1;
        // update
        keyCountMap.put(key, newCount);
        if (!countToKeysMap.containsKey(newCount)) {
            countToKeysMap.put(newCount, new LinkedHashSet<>());
        }
        countToKeysMap.get(newCount).add(key);
        // delete
        countToKeysMap.get(oldCount).remove(key);
        if (countToKeysMap.get(oldCount).size() == 0) {
            if (min == oldCount) {
                min = newCount;
            }
            countToKeysMap.remove(oldCount);
        }
        return val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (map.containsKey(key)) {
            map.put(key, value);
            get(key);
        } else {
            if (map.size() == capacity) {
                int evit = countToKeysMap.get(min).iterator().next();
                map.remove(evit);
                keyCountMap.remove(evit);
                countToKeysMap.get(min).remove(evit);
                if (countToKeysMap.get(min).size() == 0) {
                    countToKeysMap.remove(min);
                }
            }
            min = 1;
            countToKeysMap.putIfAbsent(min, new LinkedHashSet<>());
            countToKeysMap.get(min).add(key);
            map.put(key, value);
            keyCountMap.put(key, 1);
        }
    }

    public static void main(String[] args) {
        System.out.println("Going to test the LFU Cache Implementation");
        LFUCacheLinkedHashSet cache = new LFUCacheLinkedHashSet(2);
        cache.put(1, 10);
        cache.put(2, 20);
        System.out.println("Value for the key: 1 is " + cache.get(1)); // returns 10
        System.out.println("Value for the key: 2 is " + cache.get(2)); // returns -1 (not found)
        System.out.println("Value for the key: 2 is " + cache.get(2)); // returns -1 (not found)
        // evicts key 2 and store a key (3) with
        // value 30 in the cache.
        cache.put(3, 30);
        System.out.println("Value for the key: 1 is " + cache.get(1)); // returns 10
        System.out.println("Value for the key: 2 is " + cache.get(2)); // returns -1 (not found)
        System.out.println("Value for the key: 3 is " + cache.get(3)); // returns 30
        // evicts key 1 and store a key (4) with
        // value 40 in the cache.
        cache.put(4, 40);
        System.out.println("Value for the key: 1 is " + cache.get(1)); // returns -1 (not found)
        System.out.println("Value for the key: 2 is " + cache.get(2)); // returns 20
        System.out.println("Value for the key: 3 is " + cache.get(3)); // returns 30
        System.out.println("Value for the key: 4 is " + cache.get(4)); // return 40
    }
}
