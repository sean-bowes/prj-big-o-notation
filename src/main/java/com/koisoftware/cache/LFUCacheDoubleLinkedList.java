package com.koisoftware.cache;

import java.util.HashMap;
import java.util.Map;

public class LFUCacheDoubleLinkedList {
    class LinkedNode {
        int key;
        int val;
        int freq;
        LinkedNode prev;
        LinkedNode next;

        public LinkedNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1;
        }
    }

    class DoublyLinkedList {
        private LinkedNode head;
        private LinkedNode tail;
        private int size;

        public DoublyLinkedList() {
            this.head = new LinkedNode(-1, -1);
            this.tail = new LinkedNode(-1, -1);
            this.head.prev = null;
            this.head.next = this.tail;
            this.tail.next = null;
            this.tail.prev = this.head;
            this.size = 0;
        }

        private void addToHead(LinkedNode node) {
            LinkedNode next = this.head.next;
            next.prev = node;
            node.next = next;
            node.prev = this.head;
            this.head.next = node;
            this.size += 1;
        }

        private void remove(LinkedNode node) {
            LinkedNode prev = node.prev;
            LinkedNode next = node.next;
            prev.next = next;
            next.prev = prev;
            this.size -= 1;
        }

        private LinkedNode removeTail() {
            LinkedNode tail = this.tail.prev;
            remove(tail);
            return tail;
        }
    }

    private Map<Integer, LinkedNode> cache;
    private Map<Integer, DoublyLinkedList> freqMap;
    private int capacity;

    //used to track nodes with lowest freq
    private int minFreq;

    public LFUCacheDoubleLinkedList(int capacity) {
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        LinkedNode node = this.cache.get(key);
        if (node == null) return -1;
        updateNodeFreq(node);
        return node.val;
    }

    private void updateNodeFreq(LinkedNode node) {
        //1: increment the freq of the node
        //2: remove node from current freq list
        //3: add node to new freq list
        //4: may need to update the minFreq if minFreq list is now empty and it was the freq of oldFreq

        int oldFreq = node.freq;
        int newFreq = oldFreq + 1;
        //1
        node.freq = newFreq;
        //2
        DoublyLinkedList oldFreqList = this.freqMap.get(oldFreq);
        oldFreqList.remove(node);
        //3
        if (!this.freqMap.containsKey(newFreq)) {
            this.freqMap.put(newFreq, new DoublyLinkedList());
        }
        this.freqMap.get(newFreq).addToHead(node);
        //4
        if (oldFreqList.size == 0 && this.minFreq == oldFreq) {
            this.minFreq = newFreq;
        }
    }

    public void put(int key, int value) {
        if (this.capacity < 1) return;
        LinkedNode node = this.cache.get(key);
        if (node != null) {
            updateNodeFreq(node);
            node.val = value;
            return;
        }
        //need to insert a new node
        boolean shouldEvict = this.cache.size() >= this.capacity;
        //before inserting remove the lru of the least freq nodes
        if (shouldEvict) {
            DoublyLinkedList evictList = this.freqMap.get(this.minFreq);
            LinkedNode evictedNode = evictList.removeTail();
            this.cache.remove(evictedNode.key);
        }
        //now add the new node
        node = new LinkedNode(key, value);
        this.cache.put(key, node);
        //since we are adding a new node the min freq will revert to 1
        this.minFreq = 1;
        if (!this.freqMap.containsKey(1)) {
            this.freqMap.put(1, new DoublyLinkedList());
        }
        this.freqMap.get(1).addToHead(node);
    }

    public static void main(String[] args) {
        System.out.println("Going to test the LFU Cache Implementation");
        LFUCacheDoubleLinkedList cache = new LFUCacheDoubleLinkedList(2);
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
