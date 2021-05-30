package com.koisoftware.twinsigma;

import com.koisoftware.sorting.QuickSortByComparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunWithAnagrams {

    private final Comparator<Integer> ascOrderComparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    };

    public String[] funWithAnagramsByArray(String[] text) {
        //Two strings are anagrams if they are permutations of each other. In other words, both strings have the same size and the same characters. For example, "aaagmnrs" is an anagram of "anagrams". Given an array of strings, remove each string that is an anagram of an earlier string, then return the remaining array in sorted order.
        //<p><em>str = ['code', 'doce', 'ecod', 'framer', 'frame']</em></p>
        //<ul>
        //	<li>"<em>code"</em> and "<em>doce"</em> are anagrams. Remove "doce" from the array and keep the first occurrence <em>"code"</em> in the array.</li>
        //	<li>
        //<em>"code"&nbsp;</em>and "<em>ecod"</em>&nbsp;are anagrams. Remove "ecod" from the array&nbsp;and keep the first occurrence <em>"code"</em> in the array.</li>
        //	<li>
        //<em>"code"</em> and "<em>framer"</em> are not anagrams<em>.</em>&nbsp;Keep both strings in the array.</li>
        //	<li>"<em>framer"</em> and "<em>frame"</em> are not anagrams due to the extra <em>'r'</em>&nbsp;in <em>'framer'.</em>&nbsp;Keep both strings in the array.</li>
        //	<li>Order the remaining strings in ascending order: <em>[ "code","frame","framer"]</em>.</li>
        //</ul>
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < text.length; i++) {
            Comparator<Character> cm = Comparator.comparing((Character x) -> x);
            Character[] ch = new Character[text[i].length()];
            char[] cq = text[i].toCharArray();
            for (int j = 0; j < cq.length; j++) {
                ch[j] = Character.valueOf(cq[j]);
            }
            Character[] sorted = QuickSortByComparator.quickSort(ch, cm);
            Stream<Character> charStream = Arrays.stream(sorted);
            String s = charStream.map(String::valueOf).collect(Collectors.joining());
            System.out.println("s=" + s);
            if (map.get(s) == null) {
                map.put(s, text[i]);
            }
        }
        String[] r = map.values().toArray(new String[0]);
        return r;
    }

    public List<String> funWithAnagramsByList(List<String> text) {
        return null;
    }

    private void printArray(String arr[]) {
        int i;
        for (i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }

    public static void main(String args[]) {
        FunWithAnagrams obj = new FunWithAnagrams();
        String[] arr = new String[4];
        arr[0] = "code";
        arr[1] = "aaagmnrs";
        arr[2] = "anagrams";
        arr[3] = "doce";
        obj.printArray(obj.funWithAnagramsByArray(arr));
    }
}
