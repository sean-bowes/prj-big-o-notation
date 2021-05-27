package com.koisoftware.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NaryTreeNode<T> {
    private T value;
    private List<NaryTreeNode<T>> children;
    private NaryTreeNode<T> parent;

    public NaryTreeNode(NaryTreeNode<T> node) {
        this.value = node.getValue();
        children = new ArrayList<NaryTreeNode<T>>();
    }

    public NaryTreeNode(T value) {
        this.value = value;
        this.children = new LinkedList<NaryTreeNode<T>>();
    }

    public NaryTreeNode() {
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<NaryTreeNode<T>> getChildren() {
        return children;
    }

    public NaryTreeNode<T> getParent() {
        return this.parent;
    }

    public void setParent(NaryTreeNode<T> parent) {
        this.parent = parent;
    }
}
