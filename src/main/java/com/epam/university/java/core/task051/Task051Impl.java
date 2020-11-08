package com.epam.university.java.core.task051;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task051Impl implements Task051 {
    @Override
    public String replace(String source) {
        if (source == null || source.isBlank() || source.matches("[^a-z]+")
                || source.equals("the")) {
            throw new IllegalArgumentException();
        }
        List<String> list = new ArrayList<>(Arrays.asList(source.split(" ")));
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).equals("the") && isVowel(list.get(i).charAt(0))) {
                list.set(i - 1, "an");
            } else if (list.get(i - 1).equals("the")) {
                list.set(i - 1, "a");
            }
        }
        return StringUtils.join(list, " ");
    }

    private boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

}
