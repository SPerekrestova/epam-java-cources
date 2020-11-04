package com.epam.university.java.core.task045;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Task045Impl implements Task045 {
    @Override
    public String doAnagram(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        if (input.length() == 1) {
            return input;
        }
        if (input.isBlank()) {
            return input;
        }
        if (input.matches("[^a-zA-Z]+")) {
            return input;
        }
        StringBuilder sb;
        if (input.split("\\s").length > 1) {
            sb = processMulti(input);
        } else {
            sb = processSingle(input);
        }

        return sb.toString().trim();
    }

    private StringBuilder processMulti(String input) {
        StringBuilder sb = new StringBuilder();
        for (String s : input.split(" ")) {
            sb.append(processSingle(s));
            sb.append(" ");
        }
        return sb;
    }

    private StringBuilder processSingle(String input) {
        StringBuilder sb = new StringBuilder();
        char[] arr = input.toCharArray();
        ArrayList<Character> charList = new ArrayList<>();
        List<Character> arrList = new LinkedList<>();
        convertToLists(arr, charList, arrList);
        int count = arrList.size() - 1;

        for (int i = 0; i < charList.size(); i++) {
            if (Character.isLetter(charList.get(i))) {
                charList.set(i, arrList.get(count));
                count--;
            }
        }
        String result = charList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
        sb.append(result);
        return sb;
    }

    private void convertToLists(char[] arr, ArrayList<Character> charList,
                                List<Character> arrList) {
        for (char c : arr) {
            if (Character.isLetter(c)) {
                arrList.add(c);
            }
            charList.add(c);
        }
    }

}
