package com.koisoftware.strings;

public class FirstNonRepeatingCharacters {
    static final int NO_OF_CHARS = 256;
    static char count[] = new char[NO_OF_CHARS];

    /**
     * calculate count of characters
     * in the passed string
     */
    static void getCharCountArray(String str) {
        for (int i = 0; i < str.length(); i++)
            count[str.charAt(i)]++;
    }

    /**
     * The method returns index of first non-repeating
     * character in a string. If all characters are repeating
     * then returns -1
     */
    static int firstNonRepeating(String str) {
        getCharCountArray(str);
        int index = -1, i;
        for (i = 0; i < str.length(); i++) {
            if (count[str.charAt(i)] == 1) {
                index = i;
                break;
            }
        }
        return index;
    }

    // Function to return the last non-repeating character
    static String lastNonRepeating(String str, int n) {
        // To store the frequency of each of
        // the character of the given string
        int freq[] = new int[NO_OF_CHARS];
        // Update the frequencies
        for (int i = 0; i < n; i++) {
            freq[str.charAt(i)]++;
        }
        // Starting from the last character
        for (int i = n - 1; i >= 0; i--) {
            // Current character
            char ch = str.charAt(i);
            // If frequency of the current character is 1
            // then return the character
            if (freq[ch] == 1)
                return ("" + ch);
        }
        // All the characters of the
        // string are repeating
        return "-1";
    }

    public static void main(String[] args) {
        String str = "kidselubfortotskidsblue";
        int index = firstNonRepeating(str);
        System.out.println("str=" + str);
        System.out.println(
                index == -1
                        ? "Either all characters are repeating or string "
                        + "is empty"
                        : "First non-repeating character is "
                        + str.charAt(index));
        System.out.println("Last non repeating character is " + lastNonRepeating(str, str.length()));
    }
}
