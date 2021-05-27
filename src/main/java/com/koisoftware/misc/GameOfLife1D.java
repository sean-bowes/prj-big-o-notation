package com.koisoftware.misc;

public class GameOfLife1D {

    public class OneDimGameOfLife {
        int[] board;

        public OneDimGameOfLife(int[] board) {
            this.board = board;
        }

        /**
         * states:
         * current\next | alive | dead
         * alive        |   1   |  -1
         * dead         |   2   |  0
         */
        public void next() {
            for (int i = 0; i < board.length; i++) {
                int numAliveNeighbours = countAliveNeighbours(i);
                if (board[i] == 1) {
                    if (numAliveNeighbours != 2 && numAliveNeighbours != 4) {
                        board[i] = -1;
                    }
                } else {
                    if (numAliveNeighbours == 2 || numAliveNeighbours == 3) {
                        board[i] = 2;
                    }
                }
            }
            for (int i = 0; i < board.length; i++) {
                board[i] = board[i] > 0 ? 1 : 0;
            }
        }

        public int[] getBoard() {
            return board;
        }

        private int countAliveNeighbours(int index) {
            int count = 0;
            for (int i = -2; i <= 2; i++) {
                if (i == 0) continue;
                if (index + i >= 0 && index + i < board.length) {
                    count += board[index + i] % 2 != 0 ? 1 : 0;
                }
            }
            return count;
        }
    }

    public static void main(String[] args) {
        GameOfLife1D obj = new GameOfLife1D();
        int dimension = 20;
        int steps = 20;
        double threshold = 0.5;
        int[] board = new int[dimension];
        for (int i = 0; i < dimension; i++) {
            board[i] = Math.random() >= threshold ? 0 : 1;
        }
        for (int j = 0; j < dimension; j++) {
            System.out.printf("%s ", board[j] == 0 ? "." : "*");
        }
        System.out.printf("\n");
        OneDimGameOfLife game = obj.new OneDimGameOfLife(board);
        for (int i = 0; i < steps; i++) {
            game.next();
            int[] newBoard = game.getBoard();
            for (int j = 0; j < dimension; j++) {
                System.out.printf("%s ", newBoard[j] == 0 ? "." : "*");
            }
            System.out.printf("\n");
        }
    }
}
