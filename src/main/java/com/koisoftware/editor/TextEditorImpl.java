package com.koisoftware.editor;

import java.util.Stack;

public class TextEditorImpl implements ITextEditor {

    private TextEditorTreeNode root;
    private Stack<EditAction> redoStack;
    private Stack<EditAction> undoStack;
    private static final int INITIALSIZE = 5;

    public enum EditType {
        INSERT, DELETE;
    }

    public class EditAction {
        public EditType type;
        public int start;
        public int end;
        public String data;

        public EditAction(EditType type, int start, int end, String data) {
            this.type = type;
            this.start = start;
            this.end = end;
            this.data = data;
        }
    }

    public TextEditorImpl() {
        redoStack = new Stack<>();
        undoStack = new Stack<>();
    }

    public void makeEmpty() {
        root = null;
    }

    @Override
    public void initialize(String str) {
        for (int i = 0; i < str.length(); i += INITIALSIZE) {
            if (i + INITIALSIZE < str.length()) {
                concat(new TextEditorTreeNode(str.substring(i, i + INITIALSIZE)));
            } else {
                concat(new TextEditorTreeNode(str.substring(i)));
            }
        }
    }

    @Override
    public char index(int index) {
        TextEditorTreeNode curr = root;
        if (curr == null) return ' ';
        return index(curr, index);
    }

    @Override
    public void insert(int p1, String s) {
        TextEditorTreeNode newNode = new TextEditorTreeNode(s);
        TextEditorTreeNode lastNode = split(p1);
        concat(newNode);
        concat(lastNode);
        undoStack.push(new EditAction(EditType.DELETE, p1, p1 + s.length(), s));
    }

    @Override
    public void delete(int p1, int p2) {
        if (p1 >= p2) {
            return;
        }
        TextEditorTreeNode lastNode = split(p2);
        TextEditorTreeNode deleteNode = split(p1);
        concat(lastNode);
        undoStack.push(new EditAction(EditType.INSERT, p1, p2, print(deleteNode)));
    }

    @Override
    public void highlight(int p1, int p2) {
    }

    @Override
    public void redo() throws Exception {
        EditAction action = redoStack.pop();
        switch (action.type) {
            case INSERT:
                delete(action.start, action.end);
                break;
            case DELETE:
                insert(action.start, action.data);
                break;
            default:
                throw new Exception("Invalid Redo Type");
        }
    }

    @Override
    public void undo() throws Exception {
        EditAction action = undoStack.pop();
        redoStack.push(action);
        switch (action.type) {
            case INSERT:
                insert(action.start, action.data);
                break;
            case DELETE:
                delete(action.start, action.end);
                break;
            default:
                throw new Exception("Invalid Undo Type");
        }
    }

    /**
     * Function to print Rope
     **/
    @Override
    public void print() {
        System.out.println(print(root));
    }

    private char index(TextEditorTreeNode node, int index) {
        if (node.weight <= index && node.right != null) {
            return index(node.right, index - node.weight);
        }
        if (node.left != null) return index(node.left, index);
        return node.data.charAt(index);
    }

    private String print(TextEditorTreeNode r) {
        String s = "";
        if (r != null) {
            s = s + print(r.left);
            if (r.data != null)
                s = s + r.data;
            s = s + print(r.right);
        }
        return s;
    }

    // most of the actions can be done by concat and split.

    /**
     * Concate the new Node to the right side of the current string
     *
     * @param newNode
     */
    private void concat(TextEditorTreeNode newNode) {
        if (root == null) {
            root = newNode;
            return;
        }

        TextEditorTreeNode newRoot = new TextEditorTreeNode();
        newRoot.left = root;
        newRoot.right = newNode;
        newRoot.weight = newRoot.left.weight;
        if (newRoot.left.right != null)
            newRoot.weight += newRoot.left.right.weight;
        root = newRoot;

        // balance tree after each concat
    }

    /**
     * Split the current string at ind, and return the right side root node
     * current root point to the left side root
     *
     * @param ind
     * @return right side root
     */
    private TextEditorTreeNode split(int index) {
        // in-order traverse
        TextEditorTreeNode node = root;
        return split(node, index);
    }

    private TextEditorTreeNode split(TextEditorTreeNode node, int index) {
        TextEditorTreeNode curr = null;
        if (index < node.weight && node.left != null) {
            curr = split(node.left, index);
            if (node.left.data != null && node.left.data.length() == 0) node.left = null;
            // new node
            TextEditorTreeNode next = new TextEditorTreeNode();
            next.left = curr;
            next.right = node.right;
            next.weight = curr.weight;
            if (curr.right != null) next.weight += curr.right.weight;
            node.right = null;
            if (node != root) node = node.left;
            else node.weight = node.left.weight + (node.left.right != null ? node.left.right.weight : 0);
            curr = next;
        } else if (index >= node.weight && node.right != null) {
            curr = split(node.right, index - node.weight);
            root = node;
        } else {
            // leaf node, splite the string, return right side
            String currString = node.data;
            curr = new TextEditorTreeNode(currString.substring(index));
            node.update(currString.substring(0, index));
        }
        return curr;
    }
}
