package com.epam.university.java.core.task049;

public class Task049Impl implements Task049 {
    @Override
    public String getResultList(String first, String second) {
        if (first == null || second == null || first.isBlank() || second.isBlank()) {
            throw new IllegalArgumentException();
        }
        if (first.equals(second)) {
            return first;
        }
        StringBuilder result;
        result = first.length() > second.length()
                ? findCommon(first, second.toCharArray()) :
                findCommon(second, first.toCharArray());
        return result.toString();
    }

    private StringBuilder findCommon(String str, char[] arr) {
        StringBuilder result = new StringBuilder();
        boolean isFirst = true;
        for (int i = 0; i < arr.length; i++) {
            String buf = String.valueOf(arr[i]);
            if (isFirst && str.contains(buf)) {
                result.append(buf);
                isFirst = false;
            } else if (str.contains(result + buf)) {
                result.append(buf);
            }
        }
        return result;
    }
}
