package com.koisoftware.editor;

public class TextEditorDemo {
    public static void main(String[] args) {
        TextEditorImpl textEditor = new TextEditorImpl();
        textEditor.initialize("0123456789");
        textEditor.print();
        textEditor.delete(2, 4);
        textEditor.print();
        textEditor.insert(2, "23");
        textEditor.print();
        try {
            textEditor.undo();
            textEditor.print();
            textEditor.redo();
            textEditor.print();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}