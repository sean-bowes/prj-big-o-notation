package com.koisoftware.iterators;

import java.util.*;

public class IteratorInIterator<T> implements Iterable<T> {
    List<List<Iterator<T>>> listOfLists;
    int listNum, listIndex, totalIterator, lastListIndex, lastItIndex, maxListSize;
    int[] itSize, itIndex;

    public IteratorInIterator(List<List<Iterator<T>>> listOfLists) {
        this.listOfLists = listOfLists;
        listNum = listOfLists.size();
        itSize = new int[listNum];
        itIndex = new int[listNum];
        listIndex = 0;
        lastListIndex = 0;
        lastItIndex = 0;
        maxListSize = 0;
        for (int i = 0; i < listNum; i++) {
            itSize[i] = listOfLists.get(i).size();
            itIndex[i] = -1;
            if (maxListSize < itSize[i])
                maxListSize = itSize[i];
        }
        itIndex[0] = 0;
        totalIterator = maxListSize * listNum;
    }

    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            private boolean goToNextIndex() {
                int count = 0;
                do {
                    listIndex++;
                    if (listIndex == listNum) {
                        listIndex = 0;
                    }
                    itIndex[listIndex]++;
                    if (itIndex[listIndex] == itSize[listIndex])
                        itIndex[listIndex] = 0;
                    count++;

                } while (!listOfLists.get(listIndex).get(itIndex[listIndex]).hasNext() && (count < totalIterator));
                return (count < totalIterator);
            }

            public boolean hasNext() {
                return (listOfLists.get(listIndex).get(itIndex[listIndex]).hasNext());
            }

            public T next() {
                T t = listOfLists.get(listIndex).get(itIndex[listIndex]).next();
                lastListIndex = listIndex;
                lastItIndex = itIndex[listIndex];
                goToNextIndex();
                return t;
            }

            public void remove() {
                listOfLists.get(lastListIndex).get(lastListIndex).remove();
            }
        };
        return it;
    }

    public static void main(String[] args) {
        List<Integer> l11 = new ArrayList<>();
        List<Integer> l21 = new ArrayList<>();
        List<Integer> l22 = new ArrayList<>();

        Iterator<Integer> it11, it21, it22;

        List<Iterator<Integer>> l1 = new LinkedList<>(), l2 = new Vector<>();
        List<List<Iterator<Integer>>> l = new ArrayList<>();

        l11.add(3);
        l11.add(2);
        l11.add(1);

        l21.add(31);
        l21.add(32);

        l22.add(52);
        l22.add(42);
        l22.add(32);
        l22.add(22);
        l22.add(11);

        it11 = l11.iterator();
        it21 = l21.iterator();
        it22 = l22.iterator();

        l1.add(it11);

        l2.add(it21);
        l2.add(it22);

        l.add(l1);
        l.add(l2);

        IteratorInIterator<Integer> superIt = new IteratorInIterator<>(l);
        for (Integer i : superIt) {
            System.out.print(i + ", ");
        }
    }

}
