package com.epam.university.java.core.task045;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task045Impl implements Task045 {
    @Override
    public String doAnagram(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder();
        List<String> inputList = new ArrayList<>(Arrays.asList(input.split(" ")));
        if (!inputList.isEmpty()) {
            for (String str : inputList) {
                if (reverse(str, sb)) {
                    return input;
                }
                sb.append(" ");
            }
        } else {
            if (reverse(input, sb)) {
                return input;
            }
        }
        return sb.toString().trim();
    }

    private boolean reverse(String input, StringBuilder sb) {
        char[] arr = input.toCharArray();
        for (int i = arr.length - 1; i > -1; i--) {
            if (Character.isLetter(arr[i])) {
                sb.append(arr[i]);
            }
        }
        if (sb.length() == 0) {
            return true;
        }
        return false;
    }
}
