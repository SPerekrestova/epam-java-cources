package com.epam.university.java.core.task058;

public class Task058Impl implements Task058 {
    @Override
    public int[][] fillSpiral(int n) {
        if (n == 0) {
            throw new IllegalArgumentException();
        }
        if (n == 1) {
            return new int[][]{{1}};
        }
        int[][] result = new int[n][n];
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;
        int dir = 1;
        int number = 1;

        while (top <= bottom && left <= right) {

            if (dir == 1) {
                for (int i = left; i <= right; ++i) {
                    result[top][i] = number;
                    number++;
                }
                ++top;
                dir = 2;
            } else if (dir == 2) {
                for (int i = top; i <= bottom; ++i) {
                    result[i][right] = number;
                    number++;
                }
                --right;
                dir = 3;
            } else if (dir == 3) {
                for (int i = right; i >= left; --i) {
                    result[bottom][i] = number;
                    number++;
                }
                --bottom;
                dir = 4;
            } else {
                for (int i = bottom; i >= top; --i) {
                    result[i][left] = number;
                    number++;
                }
                ++left;
                dir = 1;
            }
        }
        return result;
    }
}
