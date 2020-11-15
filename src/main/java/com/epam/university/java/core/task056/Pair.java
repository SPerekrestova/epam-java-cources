package com.epam.university.java.core.task056;

import java.util.Objects;

public class Pair implements Comparable<Pair>  {

    private final Integer key;
    private final IntPair value;

    public Pair(Integer key, IntPair value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public IntPair getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair pair = (Pair) o;
        return Objects.equals(key, pair.key)
                && Objects.equals(value, pair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public int compareTo(Pair o) {
        return (Integer.compare(this.value.getLeft(), o.value.getLeft()));
    }
}
