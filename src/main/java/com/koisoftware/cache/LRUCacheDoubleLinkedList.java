package com.koisoftware.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LRUCacheDoubleLinkedList {

    // LRU is a cache eviction algorithm called least recently used cache.
    // LFU is a cache eviction algorithm called least frequently used cache.

    // LRU uses a hash table to cache the entries and a double linked list to keep track of the access order.
    // If an entry is inserted, updated or accessed, it gets removed and re-linked before the head node.
    // The node before head is the most recently used and the node after is the eldest node.
    // When the cache reaches its maximum size the least recently used entry will be evicted from the cache.

    // LFU requires three data structures. One is a hash table which is used to cache the key/values so that
    // given a key we can retrieve the cache entry at O(1). Second one is a double linked list for each
    // frequency of access. The max frequency is capped at the cache size to avoid creating more and more
    // frequency list entries. If we have a cache of max size 4 then we will end up with 4 different frequencies.
    // Each frequency will have a double linked list to keep track of the cache entries belonging to that
    // particular frequency. The third data structure would be to somehow link these frequencies lists.
    // It can be either an array or another linked list so that on accessing a cache entry it can be easily
    // promoted to the next frequency list in time O(1).

    class DLinkedNode {
        public int key;
        public int value;
        public DLinkedNode prev;
        public DLinkedNode next;

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private DLinkedNode head;
    private DLinkedNode tail;
    private Map<Integer, DLinkedNode> map;
    private int capacity;
    private int size;

    public LRUCacheDoubleLinkedList(int capacity) {
        this.capacity = capacity;
        size = 0;
        map = new HashMap<Integer, DLinkedNode>();
        Collections.synchronizedMap(map);
        head = new DLinkedNode(-1, -1);
        tail = new DLinkedNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        DLinkedNode node = map.get(key);
        removeNode(node);
        addNode(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        if (map.containsKey(key)) {
            DLinkedNode node = map.get(key);
            node.value = value;
            removeNode(node);
            addNode(node);
        } else {
            if (size == capacity) {
                map.remove(tail.prev.key);
                removeNode(tail.prev);
                size--;
            }
            DLinkedNode node = new DLinkedNode(key, value);
            map.put(key, node);
            addNode(node);
            size++;
        }
    }

    private void addNode(DLinkedNode node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    public static void main(String[] args) {
        System.out.println("Going to test the LRU Cache Implementation");
        LRUCacheDoubleLinkedList cache = new LRUCacheDoubleLinkedList(2);
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
