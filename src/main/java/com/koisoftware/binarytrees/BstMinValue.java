package com.koisoftware.binarytrees;

/**
 * Find the smallest value in a binary search tree.
 * <p>
 * Signature of expected method:
 * <p>
 * public static <T> T minValue(TreeNode<T> root) {...}
 */
public class BstMinValue {
    public static <T> T minValue(TreeNode<T> root) {
        if (root == null) {
            return null;
        }

        while (root.getLeft() != null) {
            root = root.getLeft();
        }

        return root.getValue();
    }
}
