package com.epam.university.java.core.task039;

public class Node implements Comparable<Node> {
    private int frequency;
    private char letter;
    Node left;
    Node right;


    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }


    @Override
    public int compareTo(Node o) {
        return frequency - o.frequency;
    }
}
