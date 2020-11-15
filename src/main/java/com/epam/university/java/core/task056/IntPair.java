package com.epam.university.java.core.task056;

import java.util.Objects;

public class IntPair implements Comparable<IntPair> {

    final int left;
    final int right;

    int getRight() {
        return right;
    }

    int getLeft() {
        return left;
    }

    IntPair(int left, int right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IntPair intPair = (IntPair) o;
        return getLeft() == intPair.getLeft()
                && getRight() == intPair.getRight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeft(), getRight());
    }

    @Override
    public int compareTo(IntPair pair) {
        return (Integer.compare(this.getLeft(), pair.getLeft()));
    }
}
