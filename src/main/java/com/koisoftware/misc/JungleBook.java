package com.koisoftware.misc;

import java.util.HashMap;
import java.util.HashSet;

public class JungleBook {
    public int maxGroups(int[] arr) {
        //time complexity O(n^2)
        //time complexity O(nm)
        //time complexity of this solution is the number of items in the array times the average length of the path.
        int max = 1;
        int arrLen = arr.length;
        for (int i = 0; i < arr.length; i++) {
            int a = i;
            int counter = 1;
            while (arr[a] > -1 && arr[a] < arrLen && counter < arrLen) {
                a = arr[a];
                //System.out.println("a=" + a);
                counter++;
            }
            if (counter > max) {
                max = counter;
            }
        }
        return max;
    }

    private class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private HashMap<Integer, Integer> nodeMapByIndex = new HashMap<>(); // index key - parent value
    private HashMap<Integer, HashSet<Integer>> nodeMapByParent = new HashMap<>(); //parent key - index values
    private HashMap<Integer, Node> nodeMap = new HashMap<>();

    public void insertNodeToParent(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            nodeMapByIndex.put(i, arr[i]);
            if (nodeMapByParent.get(arr[i]) == null) {
                HashSet<Integer> h = new HashSet<>();
                h.add(i);
                nodeMapByParent.put(arr[i], h);
            } else {
                nodeMapByParent.get(arr[i]).add(i);
            }
        }
        for (Integer i : nodeMapByParent.get(-1)) {
            nodeMap.put(i, new Node(i));
        }
        //todo: not finished
    }

    public static void main(String args[]) {
        JungleBook obj = new JungleBook();
        int[] arr = {-1, 8, 6, 0, 7, 3, 8, 9, -1, 6, 1};
        System.out.println("max=" + obj.maxGroups(arr));
        obj.insertNodeToParent(arr);
//        System.out.println("nodeMap=" + obj.nodeMap.size());
//        int[] arr2 = {-1, 0, 1};
//        System.out.println("max=" + obj.maxGroups(arr2));
//      The groups will be: [0,8], [3,1,6], [5,10,2,9], [7], [4]

    }
}
