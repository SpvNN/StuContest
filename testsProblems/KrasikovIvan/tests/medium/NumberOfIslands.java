package medium;

import java.util.*;

public class NumberOfIslands {
    public static int solution(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }

    public static void dfs(char[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] != '1') {
            return;
        }

        grid[row][col] = '0';

        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

    public static char[][] generateRandomGrid() {
        int rows = (int) (Math.random() * 10) + 1; // Случайное количество строк от 1 до 10
        int cols = (int) (Math.random() * 10) + 1; // Случайное количество столбцов от 1 до 10
        char[][] grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int rand = (int) (Math.random()*2); // Случайное число 0 или 1
                grid[i][j] = (rand == 0) ? '0' : '1';
            }
        }

        return grid;
    }

    public static Map<char[][], Integer> testGeneration(int n) {
        Map<char[][], Integer> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Test " + (i + 1) + ":");
            char[][] grid = generateRandomGrid();
            System.out.println("Input:");
            printGrid(grid);
            int result = solution(grid);
            tests.put(grid, result);
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }

    public static void printGrid(char[][] grid) {
        for (char[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }
}
