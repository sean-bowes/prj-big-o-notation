package com.koisoftware.misc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class GameOfLifeInfinite {

    class BoardMap {
        private Map<Integer, Map<Integer, Integer>> map;

        public BoardMap() {
            map = new HashMap<>();
        }

        public boolean contains(int i, int j) {
            return map.containsKey(i) && map.get(i).containsKey(j);
        }

        public void put(int i, int j, int val) {
            map.computeIfAbsent(i, x -> new HashMap<>()).put(j, val);
        }

        public void update(int i, int j, int delta) {
            int val = delta;
            if (contains(i, j)) {
                val += get(i, j);
            }
            put(i, j, val);
        }

        public int get(int i, int j) throws NoSuchElementException {
            if (contains(i, j)) {
                return map.get(i).get(j);
            }
            throw new NoSuchElementException(String.format("Cannot find element at row %d column %d.", i, j));
        }

        public Iterator<Entry<Integer, Map<Integer, Integer>>> getRowIterator() {
            return map.entrySet().iterator();
        }
    }

    class InfiniteBoardGameOfLife {
        BoardMap lives;

        public InfiniteBoardGameOfLife(BoardMap lives) {
            this.lives = lives;
        }

        public void getNextState() {
            BoardMap counter = getCounter();
            BoardMap nextState = new BoardMap();
            Iterator<Entry<Integer, Map<Integer, Integer>>> iter = counter.getRowIterator();
            while (iter.hasNext()) {
                Entry<Integer, Map<Integer, Integer>> row = iter.next();
                int currRow = row.getKey();
                row.getValue().entrySet().stream().forEach(column -> {
                    int count = column.getValue();
                    int currCol = column.getKey();
                    if (count == 3 || (lives.contains(currRow, currCol) && count == 2)) {
                        nextState.put(currRow, currCol, 1);
                    }
                });
            }
            lives = nextState;
        }

        public void printAlives() {
            Iterator<Entry<Integer, Map<Integer, Integer>>> iter = lives.getRowIterator();
            while (iter.hasNext()) {
                Entry<Integer, Map<Integer, Integer>> row = iter.next();
                int currRow = row.getKey();
                row.getValue().entrySet().stream().forEach(a -> {
                    int currCol = a.getKey();
                    System.out.printf(" {%d, %d} ", currRow, currCol);
                });
            }
            System.out.printf("\n");
        }

        private BoardMap getCounter() {
            BoardMap counter = new BoardMap();
            Iterator<Entry<Integer, Map<Integer, Integer>>> iter = lives.getRowIterator();
            while (iter.hasNext()) {
                Entry<Integer, Map<Integer, Integer>> row = iter.next();
                int currRow = row.getKey();
                row.getValue().entrySet().stream().forEach(a -> {
                    int currCol = a.getKey();
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (i == 0 && j == 0) continue;
                            counter.update(currRow + i, currCol + j, 1);
                        }
                    }
                });
            }
            return counter;
        }
    }

    public static void main(String[] args) {
        GameOfLifeInfinite obj = new GameOfLifeInfinite();
        BoardMap lives = obj.new BoardMap();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                lives.put(i, j, Math.random() >= 0.5 ? 0 : 1);
            }
        }
        InfiniteBoardGameOfLife game = obj.new InfiniteBoardGameOfLife(lives);
        for (int i = 0; i < 10; i++) {
            game.getNextState();
        }
        game.printAlives();
    }

}
