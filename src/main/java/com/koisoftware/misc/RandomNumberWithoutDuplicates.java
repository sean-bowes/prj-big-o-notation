package com.koisoftware.misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class RandomNumberWithoutDuplicates {
    ArrayList<Integer> randomList = new ArrayList<>();
    HashSet<Integer> returnItemList = new HashSet<>();

    public void generateRandomList(int st, int nm) {
        randomList.clear();
        for (int i = st; i <= nm; i++) {
            randomList.add(i);
        }
        randomList.removeAll(returnItemList);
    }

    public List<Integer> getRandomWithoutDuplicates(int nm) {
        ArrayList<Integer> returnList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < nm; i++) {
            int index = rand.nextInt(randomList.size());
//            int cnt = 0;
//            while (!returnItemList.contains(randomList.get(index)) && cnt < nm) {
//                index = rand.nextInt(randomList.size());
//                cnt++;
//            }
            returnList.add(randomList.remove(index));
        }
        returnItemList.addAll(returnList);
        return returnList;
    }

    public static void main(String args[]) throws Exception {
        int startIndex = 1;
        int size = 4;
        RandomNumberWithoutDuplicates obj = new RandomNumberWithoutDuplicates();
        obj.generateRandomList(2, 20);
        System.out.println("Start List Size: " + obj.randomList.size());
        System.out.println("Start List: " + obj.randomList.toString());
        System.out.println("1 Start List Size: " + obj.randomList.size());
        System.out.println("1 Run List: " + obj.getRandomWithoutDuplicates(10).toString());
        System.out.println("2 Start List Size: " + obj.randomList.size());
        System.out.println("2 Run List: " + obj.getRandomWithoutDuplicates(2).toString());
        System.out.println("3 Start List Size: " + obj.randomList.size());
        System.out.println("3 Run List: " + obj.getRandomWithoutDuplicates(2).toString());
        obj.generateRandomList(5, 20);
        System.out.println("4 Start List Size: " + obj.randomList.size());
        System.out.println("4 Run List: " + obj.getRandomWithoutDuplicates(2).toString());

//        ArrayList<Integer> list = new ArrayList<Integer>(size);
//        for (int i = startIndex; i <= size; i++) {
//            list.add(i);
//        }
//
//        System.out.println("Start List: " + list.toString());
//        Random rand = new Random();
//        while (list.size() > 0) {
//            int index = rand.nextInt(list.size());
//            System.out.println("Selected: " + list.remove(index));
//            System.out.println("Items in List: " + list.toString());
//        }
    }
}
