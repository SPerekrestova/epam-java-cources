package com.epam.university.java.core.task047;

public class Task047Impl implements Task047 {
    private long count;

    @Override
    public long calculateInversions(int n, int[] a) {
        mergeSort(a);
        return count;
    }

    private int[] mergeSort(int[] a) {
        if (a.length == 1) {
            return a;
        }
        int n = a.length;
        int m = n / 2;
        int[] left = new int[m];
        int[] right = new int[n - m];
        System.arraycopy(a, 0, left, 0, m);
        System.arraycopy(a, m, right, 0, n - m);
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);

    }

    private int[] merge(int[] a, int[] b) {
        int i = 0;
        int j = 0;
        int[] res = new int[a.length + b.length];
        for (int k = 0; k < res.length; k++) {
            if (j == b.length || (i < a.length && a[i] <= b[j])) {
                res[k] = a[i];
                i++;
            } else {
                count += a.length - i;
                res[k] = b[j];
                j++;
            }
        }
        return res;
    }
}
