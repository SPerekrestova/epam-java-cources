package com.epam.university.java.core.task066;

public class Task066Impl implements Task066 {

    @Override
    public long repeatString(String infiniteString, long limiter) {
        if (infiniteString == null || limiter < 0) {
            throw new IllegalArgumentException();
        }
        if (limiter == 0 || !infiniteString.contains("a")) {
            return 0;
        }
        if (limiter < infiniteString.length()) {
            return getNumOfA(infiniteString.substring(0, (int) limiter));
        }
        long numOfA = getNumOfA(infiniteString);
        long buf = limiter / infiniteString.length();

        return numOfA * buf + limiter % infiniteString.length();
    }

    private long getNumOfA(String infiniteString) {
        return infiniteString.chars()
                             .filter(e -> e == 'a')
                             .count();
    }
}
