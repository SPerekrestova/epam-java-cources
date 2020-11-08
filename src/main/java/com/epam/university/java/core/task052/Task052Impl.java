package com.epam.university.java.core.task052;

import java.util.LinkedList;
import java.util.List;

public class Task052Impl implements Task052 {
    @Override
    public boolean validateCard(long number) {
        if (number <= 0) {
            throw new IllegalArgumentException();
        }
        final int checkDigit = (int) (number % 10);
        number /= 10;
        List<Integer> digitsList = getDigits(number);
        doubleValue(digitsList);
        int sum = 0;
        for (Integer n : digitsList) {
            sum += n;
        }
        int sub = sum % 10;
        return 10 - sub == checkDigit;
    }

    private void doubleValue(List<Integer> digitsList) {
        for (int i = 0; i < digitsList.size(); i += 2) {
            int buf = digitsList.get(i);
            buf *= 2;
            if (buf >= 10) {
                int buf2 = buf % 10;
                buf /= 10;
                buf += buf2;
            }
            digitsList.set(i, buf);
        }
    }

    private List<Integer> getDigits(long num) {
        List<Integer> digitsList = new LinkedList<>();
        while (num != 0) {
            if ((num % 10) == 0) {
                digitsList.add((int) (num % 10) * 100);
            } else {
                digitsList.add((int) (num % 10));
            }
            num /= 10;
        }
        return digitsList;
    }
}
