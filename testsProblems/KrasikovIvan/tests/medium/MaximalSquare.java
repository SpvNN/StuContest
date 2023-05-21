package medium;

import java.util.*;

public class MaximalSquare {
    public static int solution(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int maxSquareLen = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j] - '0';
                } else if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }

                maxSquareLen = Math.max(maxSquareLen, dp[i][j]);
            }
        }

        return maxSquareLen * maxSquareLen;
    }

    public static char[][] generateRandomMatrix() {
        int rows = (int) (Math.random() * 10) + 1; // Случайное количество строк от 1 до 10
        int cols = (int) (Math.random() * 10) + 1; // Случайное количество столбцов от 1 до 10
        char[][] matrix = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int rand = (int) (Math.random() * 2); // Случайное число 0 или 1
                matrix[i][j] = (rand == 0) ? '0' : '1';
            }
        }

        return matrix;
    }

    public static Map<char[][], Integer> testGeneration(int n) {
        Map<char[][], Integer> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char[][] matrix = generateRandomMatrix();
            int result = solution(matrix);
            tests.put(matrix, result);
            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input:");
            printMatrix(matrix);
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }

    public static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
