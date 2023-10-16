package KnightsTour;

import java.util.Arrays;

public class OneTour {
    private static final int N = 8; // Change this for different board sizes
    private static int startX; // Define the starting x-coordinate
    private static int startY; // Define the starting y-coordinate

    public static void main(String[] args) {
        int[][] board = new int[N][N];
        initializeBoard(board);

        startX = 0; // Set the starting x-coordinate
        startY = 0; // Set the starting y-coordinate
        int moveCount = 1; // Initial move count

        boolean[][] visited = new boolean[N][N];
        int[][] solution = new int[N][N];

        boolean foundSolution = solveKnightTour(board, visited, solution, startX, startY, moveCount);

        if (foundSolution) {
            System.out.println("Closed Knight's Tour solution found:");
            printBoard(solution);
        } else {
            System.out.println("No closed Knight's Tour found.");
        }
    }

    private static void initializeBoard(int[][] board) {
        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], -1); // Initialize all squares as unvisited
        }
    }

    private static boolean isSafe(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    private static boolean solveKnightTour(int[][] board, boolean[][] visited, int[][] solution, int x, int y, int moveCount) {
        if (moveCount == N * N) {
            if (isClosedTour(x, y)) {
                // Copy the solution to the board
                for (int i = 0; i < N; i++) {
                    System.arraycopy(solution[i], 0, board[i], 0, N);
                }
                printBoard(board);
                return true;
            } else {
                return false;
            }
        }

        int[] moveX = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] moveY = {1, 2, 2, 1, -1, -2, -2, -1};

        for (int i = 0; i < 8; i++) {
            int nextX = x + moveX[i];
            int nextY = y + moveY[i];

            if (isSafe(nextX, nextY) && !visited[nextX][nextY]) {
                board[nextX][nextY] = moveCount;
                visited[nextX][nextY] = true;

                if (solveKnightTour(board, visited, solution, nextX, nextY, moveCount + 1)) {
                    return true;
                }

                // Backtrack
                board[nextX][nextY] = -1;
                visited[nextX][nextY] = false;
            }
        }

        return false;
    }

    private static boolean isClosedTour(int x, int y) {
        int[] moveX = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] moveY = {1, 2, 2, 1, -1, -2, -2, -1};

        for (int i = 0; i < 8; i++) {
            int nextX = x + moveX[i];
            int nextY = y + moveY[i];
            if (nextX == startX && nextY == startY) {
                return true;
            }
        }
        return false;
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
