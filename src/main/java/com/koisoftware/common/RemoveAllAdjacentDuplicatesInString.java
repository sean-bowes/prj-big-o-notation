package com.koisoftware.common;

import java.util.Stack;

public class RemoveAllAdjacentDuplicatesInString {
    public String removeDuplicates(String s, int k) {
        // for (int i = 1, count = 1; i < s.length(); i++) {
        //     count = s.charAt(i) == s.charAt(i-1) ? count + 1 : 1;
        //     if (count == k)
        //         s = removeDuplicates(s.substring(0, i-k+1) + s.substring(i+1), k);
        // }
        // return s;
        Stack<Pair> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().c == ch) {
                stack.peek().freq++;
            } else {
                stack.push(new Pair(ch, 1));
            }
            if (stack.peek().freq == k) {
                stack.pop();
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair peek = stack.pop();
            for (int i = 0; i < peek.freq; i++) {
                sb.append(peek.c);
            }
        }
        return sb.reverse().toString();
    }

    class Pair {
        char c;
        int freq;

        public Pair(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }
}
