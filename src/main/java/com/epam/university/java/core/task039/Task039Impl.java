package com.epam.university.java.core.task039;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Task039Impl implements Task039 {
    private final Map<Character, String> charPrefixHashMap = new HashMap<>();

    @Override
    public Map<Character, String> getEncoding(Map<Character, Integer> charFrequencies) {
        Node result = build(charFrequencies);
        setPrefixCodes(result, new StringBuilder());
        return charPrefixHashMap;
    }

    @Override
    public String getEncodedString(Map<Character, Integer> charFrequencies, String string) {
        Node result;
        result = build(charFrequencies);

        setPrefixCodes(result, new StringBuilder());
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            s.append(charPrefixHashMap.get(c));
        }
        return s.toString();
    }

    @Override
    public String getDecodedString(Map<Character, Integer> charFrequencies, String encodedString) {
        Node result;
        result = build(charFrequencies);
        Node temp = result;
        setPrefixCodes(result, new StringBuilder());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < encodedString.length(); i++) {
            int j = Integer.parseInt(String.valueOf(encodedString.charAt(i)));

            if (j == 0) {
                temp = temp.getLeft();
                if (temp.getLeft() == null && temp.getRight() == null) {
                    stringBuilder.append(temp.getLetter());
                    temp = result;
                }
            }
            if (j == 1) {
                temp = temp.getRight();
                if (temp.getLeft() == null && temp.getRight() == null) {
                    stringBuilder.append(temp.getLetter());
                    temp = result;
                }
            }
        }
        return stringBuilder.toString();
    }

    private Node build(Map<Character, Integer> charFrequencies) {
        Node root = null;
        if (charFrequencies.isEmpty()) {
            throw new IllegalArgumentException();
        }
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Set<Character> keySet = charFrequencies.keySet();

        for (Character ch : keySet) {
            Node node = new Node();
            node.setLetter(ch);
            node.setFrequency(charFrequencies.get(ch));
            node.setLeft(null);
            node.setRight(null);
            priorityQueue.offer(node);
        }
        while (priorityQueue.size() > 1) {
            Node x = priorityQueue.peek();
            priorityQueue.poll();
            Node y = priorityQueue.peek();
            priorityQueue.poll();
            Node sumNode = new Node();
            sumNode.setFrequency(x.getFrequency() + y.getFrequency());
            sumNode.setLetter('-');
            if (x.getFrequency() == y.getFrequency() && priorityQueue.size() != 1) {
                sumNode.setRight(x);
                sumNode.setLeft(y);
            } else {
                sumNode.setRight(y);
                sumNode.setLeft(x);
            }
            root = sumNode;

            priorityQueue.offer(sumNode);
        }
        return root;
    }

    private void setPrefixCodes(Node node, StringBuilder prefix) {
        if (node != null) {
            if (node.getLeft() == null && node.getRight() == null) {
                charPrefixHashMap.put(node.getLetter(), prefix.toString());
            } else {
                prefix.append('0');
                setPrefixCodes(node.getLeft(), prefix);
                prefix.deleteCharAt(prefix.length() - 1);

                prefix.append('1');
                setPrefixCodes(node.getRight(), prefix);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
    }
}