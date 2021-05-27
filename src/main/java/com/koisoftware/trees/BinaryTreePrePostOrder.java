package com.koisoftware.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePrePostOrder {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> returnList = new ArrayList<Integer>();
        if (root == null) {
            return returnList;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode n = stack.pop();
            returnList.add(n.val);
            if (n.right != null) {
                stack.push(n.right);
            }
            if (n.left != null) {
                stack.push(n.left);
            }
        }
        return returnList;
    }

    /*
    //recursive
    List<Integer> result = new ArrayList<Integer>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root !=null){
            helper(root);
        }
        return result;
    }
    public void helper(TreeNode p){
        if(p.left!=null) {
            helper(p.left);
        }
        result.add(p.val);
        if(p.right!=null) {
            helper(p.right);
        }
    }
    */
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
        while (!stack.isEmpty()) {
            TreeNode t = stack.pop();
            result.add(t.val);

            t = t.right;
            while (t != null) {
                stack.push(t);
                t = t.left;
            }
        }
        return result;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.peek();
            if (temp.left == null && temp.right == null) {
                TreeNode pop = stack.pop();
                res.add(pop.val);
            } else {
                if (temp.right != null) {
                    stack.push(temp.right);
                    temp.right = null;
                }

                if (temp.left != null) {
                    stack.push(temp.left);
                    temp.left = null;
                }
            }
        }
        return res;
    }
}
