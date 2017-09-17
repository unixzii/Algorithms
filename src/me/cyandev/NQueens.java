package me.cyandev;

import java.util.Arrays;

/**
 * Encapsulates two ways to solve the N-Queens problem.
 */
public final class NQueens {

    // Avoid being instantiated
    private NQueens() {}

    public static int classic(int n) {
        int[] board = new int[n];
        int[] state = {0};

        Arrays.fill(board, -1);

        classicRecursive(board, 0, n, state);

        return state[0];
    }

    /**
     * @param board the row indices of chess in every columns
     * @param cur the current column
     * @param n the board size
     * @param state a array for outputs
     */
    private static void classicRecursive(int[] board, int cur, int n, int[] state) {
        if (cur == n) {
            state[0]++;
            return;
        }

        // For the current column, try from the top to the bottom.
        for (int i = 0; i < n; i++) {
            board[cur] = i;
            if (checkConflicts(board, cur)) {
                // This row is ok, keep going to next column.
                classicRecursive(board, cur + 1, n, state);
            }
        }
    }

    private static boolean checkConflicts(int[] board, int cur) {
        for (int i = 0; i < cur; i++) {
            // Check in a row.
            if (board[i] == board[cur]) {
                return false;
            }

            // Check diagonals.
            if (Math.abs(cur - i) == Math.abs(board[i] - board[cur])) {
                return false;
            }
        }

        return true;
    }

    public static int bitwise(int n) {
        final int complete = (1 << n) - 1;
        int[] state = { complete, 0 };

        bitwiseRecursive(0, 0, 0, state);

        return state[1];
    }

    private static void bitwiseRecursive(int ld, int col, int rd, int[] state) {
        if (col >= state[0]) {
            // We have a solution.
            state[1]++;
            return;
        }

        int possible = ~(ld | col | rd) & state[0];
        while (possible > 0) {
            final int next = possible & -possible;
            possible -= next;

            bitwiseRecursive((ld | next) >> 1, col | next, (rd | next) << 1, state);
        }
    }

}
