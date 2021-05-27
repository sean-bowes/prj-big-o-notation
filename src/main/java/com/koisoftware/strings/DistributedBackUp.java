package com.koisoftware.strings;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class DistributedBackUp {

    //Idea: First find the union of all data, and each data records an index of the data center that contains the data.
    // Then look at what is missing in the data in each data center in turn.
    public static void main(String args[]) throws Exception {
        List<Set<Integer>> inputsets = null;
        Set<Integer> inputdata = null;
        Map<Integer, Integer> reversedIndex = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inputdata = new HashSet<Integer>();
        reversedIndex = new HashMap<Integer, Integer>();

        String line = br.readLine();
        int num = Integer.parseInt(line);
        inputsets = new ArrayList<Set<Integer>>(num);
        for (int i = 0; i < num; i++) {
            String[] data = br.readLine().split(" ");
            HashSet<Integer> hs = new HashSet<Integer>(data.length);
            inputsets.add(hs);
            if (data.length == 1) {
                continue;
            }
            int size = Integer.parseInt(data[0]);
            for (int j = 1; j < size + 1; j++) {
                int tmp = Integer.parseInt(data[j]);
                hs.add(tmp);
                inputdata.add(tmp);
                if (!reversedIndex.containsKey(tmp)) {
                    reversedIndex.put(tmp, i + 1);
                }
            }
        }
        for (int i = 0; i < inputsets.size(); i++) {
            Set<Integer> s = inputsets.get(i);
            for (Integer j : inputdata) {
                if (!s.contains(j)) {
                    System.out.println(j + " " + reversedIndex.get(j) + " " + (i + 1));
                }
            }
        }
        System.out.println("done");
    }
}
