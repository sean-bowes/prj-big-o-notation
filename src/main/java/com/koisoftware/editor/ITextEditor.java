package com.koisoftware.editor;

public interface ITextEditor {
    public void initialize(String str);

    public char index(int ind);

    public void insert(int p1, String s);

    public void delete(int p1, int p2);

    public void highlight(int p1, int p2);

    public void redo() throws Exception;

    public void undo() throws Exception;

    public void print();
}
