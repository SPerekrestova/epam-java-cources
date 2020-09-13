package com.epam.university.java.core.task025;

public class Task025Impl implements Task025 {

    @Override
    public int getAmountOfAlteredLetters(String sourceMessage) {
        if (sourceMessage == null) {
            throw new IllegalArgumentException();
        }
        if (sourceMessage.isEmpty()) {
            return 0;
        }
        StringBuilder buf = new StringBuilder();
        buf.append("SOS".repeat(sourceMessage.length() / 3 + 1));

        int count = 0;

        for (int i = 0; i < sourceMessage.length(); i++) {
            if (sourceMessage.charAt(i) != buf.charAt(i)) {
                count++;
            }
        }

        return count;
    }
}
