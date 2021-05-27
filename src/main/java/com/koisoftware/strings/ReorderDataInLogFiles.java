package com.koisoftware.strings;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ReorderDataInLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (s1, s2) -> {
            int i1 = s1.indexOf(' ') + 1;
            int i2 = s2.indexOf(' ') + 1;
            int c1 = s1.charAt(i1);
            int c2 = s2.charAt(i2);
            // 4 cases:
            if (Character.isDigit(c1)) {
                if (Character.isDigit(c2)) {
                    return 0; // Digit-logs should be put in their original order
                }
                return 1; // s1 is digit-log and s2 is letter-log, s1 should come after s2
            }
            if (Character.isDigit(c2)) {
                return -1; // s2 is digit-log and s1 is letter-log, s2 should come after s1
            }
            // 4th case: s1 and s2 are both digit-logs
            int ans = s1.substring(i1).compareTo(s2.substring(i2));
            if (ans == 0) { // Break the tie by comparing identifiers
                ans = s1.substring(0, i1 - 1).compareTo(s2.substring(0, i2 - 1));
            }
            return ans;
        });
        return logs;
    }

    private void printArray(String arr[]) {
        String s;
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length - 1) {
                System.out.print("\"" + arr[i] + "\"" + ",");
            } else {
                System.out.print("\"" + arr[i] + "\"");
            }
        }
        System.out.println("");
    }

    public static void main(String[] args) throws Exception {
        ReorderDataInLogFiles obj = new ReorderDataInLogFiles();
        String[] s = {"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"};
        obj.printArray(s);
        s = obj.reorderLogFiles(s);
        obj.printArray(s);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String t = br.readLine();
        String[] myArray = t.split(",");
        s = obj.reorderLogFiles(s);
        obj.printArray(s);
    }
}
