package com.epam.university.java.core.task053;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Task053Impl implements Task053 {
    @Override
    public double calculate(String input) {
        if (
                input == null
                        || input.length() < 3
                        || input.contains(" ")
                        || input.contains("#")
        ) {
            throw new IllegalArgumentException();
        }

        input = shuntingYard(input);
        String operators = "+-*/^";
        Stack<String> stack = new Stack<>();
        List<String> tokens = new LinkedList<>(Arrays.asList(input.split(" ")));

        for (String t : tokens) {
            if (!operators.contains(t)) {
                stack.push(t);
            } else {
                double a = Double.parseDouble(stack.pop());
                double b = Double.parseDouble(stack.pop());
                switch (t) {
                    case "+":
                        stack.push(String.valueOf(a + b));
                        break;
                    case "-":
                        stack.push(String.valueOf(b - a));
                        break;
                    case "*":
                        stack.push(String.valueOf(a * b));
                        break;
                    case "/":
                        stack.push(String.valueOf(b / a));
                        break;
                    case "^":
                        stack.push(String.valueOf(Math.pow(b, a)));
                        break;
                    default:
                        break;
                }
            }
        }
        return Double.parseDouble(stack.pop());
    }

    private String shuntingYard(String input) {
        char[] in = input.toCharArray();
        Stack<Character> stack = new Stack<>();
        StringBuilder out = new StringBuilder();

        for (char c : in) {
            switch (c) {
                case '+':
                case '-':
                    while (!stack.empty() && (stack.peek() == '*'
                            || stack.peek() == '/'
                            || stack.peek() == '^')) {
                        out.append(' ');
                        out.append(stack.pop());
                    }
                    out.append(' ');
                    stack.push(c);
                    break;
                case '*':
                case '/':
                case '^':
                    out.append(' ');
                    stack.push(c);
                    break;
                case '(':
                    stack.push(c);
                    break;
                case ')':
                    while (!stack.empty() && stack.peek() != '(') {
                        out.append(' ');
                        out.append(stack.pop());
                    }
                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException();
                    }
                    stack.pop();
                    break;
                default:
                    out.append(c);
                    break;
            }
        }

        while (!stack.isEmpty()) {
            out.append(' ').append(stack.pop());
        }

        return out.toString();
    }
}
