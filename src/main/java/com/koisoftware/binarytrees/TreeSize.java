package com.koisoftware.binarytrees;

/**
 * Find the size of a binary tree.
 * <p>
 * Signature of expected method:
 * <p>
 * public static int size(TreeNode<?> root) {...}
 */
public class TreeSize {
    public static int size(TreeNode<?> root) {
        if (root == null) {
            return 0;
        }

        return size(root.getLeft()) + 1 + size(root.getRight());
    }
}
