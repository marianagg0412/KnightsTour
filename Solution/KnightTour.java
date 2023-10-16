package KnightsTour;


import java.util.ArrayList;
import java.util.List;

public class KnightTour {
    private static final int N = 6;

    public static void main(String[] args) {
        int[][] board = new int[N][N];
        initializeBoard(board);

        List<int[][]> solutions = new ArrayList<>();
        divideAndConquerKnightTour(board, solutions, 0, 0, N);


        if (solutions.isEmpty()) {
            System.out.println("No closed Knight's Tours found.");
        } else {
            for (int[][] solution : solutions) {
                printBoard(solution);
                System.out.println();
            }
        }
    }

    private static void initializeBoard(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = -1;
            }
        }
    }

    private static boolean isSafe(int[][] board, int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N && board[x][y] == -1;
    }

    private static boolean knightTour(int[][] board, int x, int y, int moveCount) {
        if (moveCount == N * N) {
            return board[x][y] == 0;
        }

        int[] moveX = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] moveY = {1, 2, 2, 1, -1, -2, -2, -1};

        List<int[]> possibleMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int nextX = x + moveX[i];
            int nextY = y + moveY[i];

            if (isSafe(board, nextX, nextY)) {
                int movesCount = countAvailableMoves(board, nextX, nextY);
                possibleMoves.add(new int[] {nextX, nextY, movesCount});
            }
        }

        possibleMoves.sort((a, b) -> Integer.compare(a[2], b[2]));

        for (int[] move : possibleMoves) {
            int nextX = move[0];
            int nextY = move[1];

            board[nextX][nextY] = moveCount;
            if (knightTour(board, nextX, nextY, moveCount + 1)) {
                return true;
            }
            board[nextX][nextY] = -1;
        }

        return false;
    }

    private static int countAvailableMoves(int[][] board, int x, int y) {
        int[] moveX = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] moveY = {1, 2, 2, 1, -1, -2, -2, -1};

        int count = 0;
        for (int i = 0; i < 8; i++) {
            int nextX = x + moveX[i];
            int nextY = y + moveY[i];
            if (isSafe(board, nextX, nextY)) {
                count++;
            }
        }
        return count;
    }

    private static void divideAndConquerKnightTour(int[][] board, List<int[][]> solutions, int startX, int startY, int subboardSize) {
        int[][] subboard = new int[subboardSize][subboardSize];

        for (int i = 0; i < subboardSize; i++) {
            for (int j = 0; j < subboardSize; j++) {
                subboard[i][j] = board[startX + i][startY + j];
            }
        }

        if (knightTour(subboard, 0, 0, 1)) {
            solutions.add(subboard);
        }

        if (subboardSize > 1) {
            int halfSize = subboardSize / 2;
            divideAndConquerKnightTour(board, solutions, startX, startY, halfSize);
            divideAndConquerKnightTour(board, solutions, startX + halfSize, startY, halfSize);
            divideAndConquerKnightTour(board, solutions, startX, startY + halfSize, halfSize);
            divideAndConquerKnightTour(board, solutions, startX + halfSize, startY + halfSize, halfSize);
        }
    }



    private static void printBoard(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%2d ", board[i][j]);
            }
            System.out.println();
        }
    }
}

