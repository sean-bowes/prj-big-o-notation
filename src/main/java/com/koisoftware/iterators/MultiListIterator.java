package com.koisoftware.iterators;

import java.util.ArrayList;
import java.util.Iterator;

public class MultiListIterator<E> implements Iterator<E> {
    private ArrayList<ArrayList<E>> useThisList;
    private int numOfList;
    private int[] sizeOfEachList;
    private int[] prevReturnedOfEachList;
    private int lastRetList;
    private int lastRetIndex;

    public MultiListIterator(ArrayList<ArrayList<E>> myList) {
        this.useThisList = myList;
        numOfList = myList.size();

        sizeOfEachList = new int[numOfList];
        prevReturnedOfEachList = new int[numOfList];
        for (int i = 0; i < numOfList; i++) {
            sizeOfEachList[i] = myList.get(i).size();
            prevReturnedOfEachList[i] = -1;
        }
        lastRetList = -1;
        lastRetIndex = -1;
    }

    public boolean hasNext() {
        int currentListIndex = lastRetList;
        int currentIndex = -1;
        int count = -1;

        do {
            currentListIndex = (currentListIndex + 1) % numOfList;
            currentIndex = prevReturnedOfEachList[currentListIndex] + 1;
            count++;
        } while (currentIndex == sizeOfEachList[currentListIndex] && count < numOfList);
        if (count == numOfList) {
            //System.out.println("False: Checked for currentList = " + currentListIndex + " and currentIndex = " + currentIndex);
            return false;
        }
        if (currentIndex < sizeOfEachList[currentListIndex]) {
            //System.out.println("True : Checked for currentList = " + currentListIndex + " and currentIndex = " + currentIndex);
            return true;
        }
        return false;
    }

    public E next() {
        int currentListIndex = lastRetList;
        int currentIndex = -1;
        int count = -1;

        do {
            currentListIndex = (currentListIndex + 1) % numOfList;
            currentIndex = prevReturnedOfEachList[currentListIndex] + 1;
            count++;
        } while (currentIndex == sizeOfEachList[currentListIndex] && count < numOfList);
        if (count == numOfList) {
            //System.out.println("Cann't Return: Checked for currentList = " + currentListIndex + " and currentIndex = " + currentIndex);
            return null;
        }
        if (currentIndex < sizeOfEachList[currentListIndex]) {
            //System.out.println("Returning : Checked for currentList = " + currentListIndex + " and currentIndex = " + currentIndex);
            lastRetList = currentListIndex;
            lastRetIndex = currentIndex;
            E temp = this.useThisList.get(lastRetList).get(lastRetIndex);
            prevReturnedOfEachList[lastRetList] = lastRetIndex;
            return temp;

        }
        return null;
    }

    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("a1");
        list1.add("a2");

        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("b1");
        list2.add("b2");
        list2.add("b3");
        list2.add("b4");

        ArrayList<String> list3 = new ArrayList<String>();
        list3.add("c1");
        list3.add("c2");
        list3.add("c3");
        list3.add("c4");
        list3.add("c5");
        list3.add("c6");

        ArrayList<ArrayList<String>> finalList = new ArrayList<ArrayList<String>>();
        finalList.add(list1);
        finalList.add(list2);
        finalList.add(list3);


        MultiListIterator itr = new MultiListIterator<String>(finalList);
        itr.hasNext();
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());


        ArrayList<Integer> list21 = new ArrayList<Integer>();
        list21.add(1);
        list21.add(2);

        ArrayList<Integer> list22 = new ArrayList<Integer>();

        list22.add(3);
        list22.add(4);
        list22.add(5);
        list22.add(6);

        ArrayList<Integer> list23 = new ArrayList<Integer>();
        list23.add(7);
        list23.add(8);
        list23.add(9);
        list23.add(10);
        list23.add(11);
        list23.add(12);

        ArrayList<ArrayList<Integer>> finalList1 = new ArrayList<ArrayList<Integer>>();
        finalList1.add(list21);
        finalList1.add(list22);
        finalList1.add(list23);

        System.out.println("Intger list");

        MultiListIterator itr1 = new MultiListIterator<Integer>(finalList1);
        itr1.hasNext();
        System.out.println(itr1.next());
        System.out.println(itr1.next());
        System.out.println(itr1.next());
        System.out.println(itr1.next());
        System.out.println(itr1.next());
        System.out.println(itr1.next());
        System.out.println(itr1.next());
        System.out.println(itr1.next());
        System.out.println(itr1.next());
        System.out.println(itr1.next());
        System.out.println(itr1.next());
        System.out.println(itr1.next());

    }
}
