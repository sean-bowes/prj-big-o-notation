package com.koisoftware.trees;

import java.util.LinkedList;
import java.util.Queue;

public class PrintTreeLevel {

    // tree node
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    // user defined pair to hold the node and its level
    static class Pair {
        Node n;
        int i;

        Pair(Node n, int i) {
            this.n = n;
            this.i = i;
        }

    }

    // print the node and level
    static void printLevel(Node root) {
        if (root == null) {
            return;
        }
        // queue to hold tree node with level
        Queue<Pair> q = new LinkedList<Pair>();
        // let root node be at level 1
        q.add(new Pair(root, 1));
        Pair p;
        // Do level Order Traversal of tree
        while (!q.isEmpty()) {
            p = q.peek();
            q.remove();
            System.out.println("Level of " + p.n.data + " is " + p.i);
            if (p.n.left != null) {
                q.add(new Pair(p.n.left, p.i + 1));
            }
            if (p.n.right != null) {
                q.add(new Pair(p.n.right, p.i + 1));
            }
        }
    }

    public static void main(String args[]) {
        Node root = null;

        /* Constructing tree given in the
              above figure */
        root = new Node(3);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(1);
        root.left.right = new Node(4);

        printLevel(root);
    }


}
