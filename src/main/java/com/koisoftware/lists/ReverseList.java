package com.koisoftware.lists;

import java.util.*;

public class ReverseList extends ArrayList<String> {

    @Override
    public Iterator<String> iterator() {

        int startIndex = this.size() - 1;
        List<String> list = this;

        Iterator<String> it = new Iterator<String>() {

            private int currentIndex = startIndex;

            @Override
            public boolean hasNext() {
                return currentIndex >= 0;
            }

            @Override
            public String next() {
                String next = list.get(currentIndex);
                currentIndex--;
                return next;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    public static void main(String[] args) {
        List<String> myList = new ReverseList();
        String[] list = {"DCBA", "ABCD", "FWER", "GETR", "HERW", "YETQ"};
        myList = Arrays.asList(list);

        //array by loop
        System.out.println("");
        System.out.println("normal array by loop");
        for (int i = 0; i < myList.size(); i++) {
            System.out.print(myList.get(i) + " ");
        }

        //reverse by loop
        System.out.println("");
        System.out.println("reverse by loop");
        for (int i = myList.size(); i-- > 0; ) {
            System.out.print(myList.get(i) + " ");
        }

        //reverse by listIterator
        System.out.println("");
        System.out.println("reverse by listIterator");
        ListIterator listIterator = myList.listIterator(myList.size());
        while (listIterator.hasPrevious()) {
            System.out.print(listIterator.previous() + " ");
        }

        //reverse by collections
        System.out.println("");
        System.out.println("reverse by collections");
        Collections.reverse(myList);
        for (int i = 0; i < myList.size(); i++) {
            System.out.print(myList.get(i) + " ");
        }

        System.out.println("");
        System.out.println("reverse by iterator");
        Iterator<String> iter = myList.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
//        myList.forEach(System.out::print);
//        System.out.println(" ");
//        myList.stream().forEach(System.out::print);
    }
}
