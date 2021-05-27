package com.koisoftware.misc;

public class PopulatingNextRightPointersInEachNode {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (root == null) return null;

        Node leftN = root.left;
        Node rightN = root.right;
        while (leftN != null) {
            leftN.next = rightN;
            leftN = leftN.right;
            rightN = rightN.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
}
