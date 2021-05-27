package com.koisoftware.iterators;

import java.util.*;

public class RandomIteratorDividableBy5 {
    public class RandomModFiveIterator implements Iterator<Integer> {
        private int nextValue;
        private boolean hasNextValue;
        private Iterator<Integer> randomIter;

        public RandomModFiveIterator(Iterator<Integer> randomIter) {
            this.nextValue = 0;
            this.hasNextValue = false;
            this.randomIter = randomIter;
        }

        @Override
        public boolean hasNext() {
            if (!hasNextValue && randomIter.hasNext()) {
                nextValue = randomIter.next();
                while (randomIter.hasNext() && nextValue % 5 != 0) {
                    nextValue = randomIter.next();
                }
                hasNextValue = (nextValue % 5) == 0;
            }
            return hasNextValue;
        }

        @Override
        public Integer next() {
            if (hasNext()) {
                hasNextValue = false;
                return nextValue;
            } else {
                throw new NoSuchElementException("No avaliable elements");
            }
        }

        public void remove() {
            randomIter.remove();
        }
    }

    public static void main(String[] args) {
        RandomIteratorDividableBy5 obj = new RandomIteratorDividableBy5();
        List<Integer> randomNums = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            randomNums.add((int) (Math.random() * 20));
        }

        Set<Integer> removeIntegers = new HashSet<>();
        removeIntegers.add(5);
        removeIntegers.add(15);

        RandomModFiveIterator iter = obj.new RandomModFiveIterator(randomNums.iterator());
        int index = 0;
        while (iter.hasNext() && index < 100) {
            int curr = iter.next();
            if (removeIntegers.contains(curr)) iter.remove();
            System.out.printf(" %d ", curr);
            index++;
        }
        System.out.println("Next round");
        iter = obj.new RandomModFiveIterator(randomNums.iterator());
        index = 0;
        while (iter.hasNext() && index < 100) {
            int curr = iter.next();
            if (removeIntegers.contains(curr)) iter.remove();
            System.out.printf(" %d ", curr);
            index++;
        }
    }
}
