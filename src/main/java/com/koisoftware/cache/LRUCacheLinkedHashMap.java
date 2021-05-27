package com.koisoftware.cache;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheLinkedHashMap {
    // LRU is a cache eviction algorithm called least recently used cache.
    // LFU is a cache eviction algorithm called least frequently used cache.

    private LinkedHashMap<Integer, Integer> map;
    private int maxSize;

    public LRUCacheLinkedHashMap(int maxSize) {
        this.maxSize = maxSize;
        map = new LinkedHashMap(maxSize, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > maxSize;
            }
        };
        Collections.synchronizedMap(map);
    }

    public int get(int key) {
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        map.put(key, value);
    }

    public static void main(String[] args) {
        System.out.println("Going to test the LRU Cache Implementation");
        LRUCacheLinkedHashMap cache = new LRUCacheLinkedHashMap(2);
        cache.put(1, 10);
        cache.put(2, 20);
        System.out.println("Value for the key: 1 is " + cache.get(1)); // returns 10
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
