package com.koisoftware.strings;

public class LongestWordInSentence {
    public static void main(String args[]) {
        String line = "Write an application to display the following pattern using the alphabet.";
        String[] words = line.split(" ");
        String word = "";
        int ctr = 0;
        for (String s : words) {
            if (s.length() > ctr) {
                word = s;
                ctr = s.length();
            }
        }
        System.out.println("word=" + word);
    }
}
