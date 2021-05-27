package com.koisoftware.editor;

class TextEditorTreeNode {
    public String data;
    public TextEditorTreeNode left;
    public TextEditorTreeNode right;
    public int weight;
    public boolean isHighLight;

    public TextEditorTreeNode(String s) {
        data = s;
        left = null;
        right = null;
        weight = s.length();
        isHighLight = false;
    }

    public TextEditorTreeNode() {
        data = null;
        left = null;
        right = null;
        weight = -1;
        isHighLight = false;
    }

    public void update(String s) {
        data = s;
        weight = s.length();
    }
}
