package com.epam.university.java.core.task063;

public class Task063Impl implements Task063 {

    @Override
    public Integer calcDigitalRoot(Integer number) {
        if (number == null) {
            throw new IllegalArgumentException();
        }
        if (number < 10) {
            return number;
        }
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        int sum2 = 0;
        while (sum != 0) {
            sum2 += sum % 10;
            sum /= 10;
        }
        int sum3 = 0;
        while (sum2 != 0) {
            sum3 += sum2 % 10;
            sum2 /= 10;
        }
        return sum3;
    }
}
