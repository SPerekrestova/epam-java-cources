package com.epam.university.java.core.task056;

import java.util.List;
import java.util.Objects;

public class Pair {

    private final Integer key;
    private final List<Integer> value;

    public Pair(Integer key, List<Integer> value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public List<Integer> getValue() {
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
}
