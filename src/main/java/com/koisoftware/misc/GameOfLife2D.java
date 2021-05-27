package com.koisoftware.misc;

public class GameOfLife2D {

    public void gameOfLife(int[][] board) {
        // state:
        // curr\next | alive | dead
        // alive     |   1   |  -1
        // dead      |   2   |  0
        int row = board.length;
        if (row == 0) return;
        int col = board[0].length;
        if (col == 0) return;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int count = 0;
                for (int k = -1; k <= 1; k++) {
                    for (int n = -1; n <= 1; n++) {
                        if (n == 0 && k == 0) continue;
                        if (i + k >= 0 && i + k < row && j + n >= 0 && j + n < col)
                            count += Math.abs(board[i + k][j + n]) == 1 ? 1 : 0;
                    }
                }
                // Rule 1 or Rule 3
                if ((board[i][j] == 1) && (count < 2 || count > 3)) {
                    // -1 signifies the cell is now dead but originally was live.
                    board[i][j] = -1;
                }
                // Rule 4
                if (board[i][j] == 0 && count == 3) {
                    // 2 signifies the cell is now live but was originally dead.
                    board[i][j] = 2;
                }
            }
        }

        // Get the final representation for the newly updated board.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] > 0) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }
}
