package com.koisoftware.strings;

import java.util.ArrayList;

public class MissingCharactersToMakePangram {
    private static ArrayList<Character> missingChars(String str, int strLength) {
        final int MAX_CHARS = 26;

        // A boolean array to store characters
        // present in string.
        boolean[] present = new boolean[MAX_CHARS];
        ArrayList<Character> charsList = new ArrayList<>();

        // Traverse string and mark characters
        // present in string.
        for (int i = 0; i < strLength; i++) {
            if ('A' <= str.charAt(i) && str.charAt(i) <= 'Z') {
                present[str.charAt(i) - 'A'] = true;
            } else if ('a' <= str.charAt(i) && str.charAt(i) <= 'z') {
                present[str.charAt(i) - 'a'] = true;
            }
        }

        // Store missing characters in alphabetic
        // order.
        for (int i = 0; i < MAX_CHARS; i++) {
            if (present[i] == false) {
                charsList.add((char) (i + 'a'));
            }
        }
        return charsList;
    }

    public static void main(String[] args) {
        // Pangram is a sentence containing every letter in the English alphabet.
        // Given a string, find all characters that are missing from the string, i.e., the characters that can make the string a Pangram
        String str = "The quick brown fox jumps over the dog";
        ArrayList<Character> missing = missingChars(str, str.length());
        if (missing.size() >= 1) {
            for (Character character : missing) {
                System.out.print(character);
            }
        }
    }
}
