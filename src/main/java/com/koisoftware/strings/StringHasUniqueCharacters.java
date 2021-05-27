package com.koisoftware.strings;

import java.util.Arrays;

public class StringHasUniqueCharacters {
    int MAX_CHAR = 256;

    boolean uniqueCharacters(String str) {
        //Time Complexity: O(n)
        if (str.length() > MAX_CHAR) {
            return false;
        }
        boolean[] chars = new boolean[MAX_CHAR];
        Arrays.fill(chars, false);
        for (int i = 0; i < str.length(); i++) {
            int index = (int) str.charAt(i);
            if (chars[index] == true) {
                return false;
            }
            chars[index] = true;
        }
        return true;
    }

    public static void main(String args[]) {
        StringHasUniqueCharacters obj = new StringHasUniqueCharacters();
        String input = "GeeksforGeeks";

        if (obj.uniqueCharacters(input)) {
            System.out.println("The String " + input + " has all unique characters");
        } else {
            System.out.println("The String " + input + " has duplicate characters");
        }

        input = "hello";
        if (obj.uniqueCharacters(input)) {
            System.out.println("The String " + input + " has all unique characters");
        } else {
            System.out.println("The String " + input + " has duplicate characters");
        }

        input = "pie";
        if (obj.uniqueCharacters(input)) {
            System.out.println("The String " + input + " has all unique characters");
        } else {
            System.out.println("The String " + input + " has duplicate characters");
        }
    }
}
