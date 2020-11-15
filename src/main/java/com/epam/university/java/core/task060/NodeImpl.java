package com.epam.university.java.core.task060;

import java.util.Objects;

public class NodeImpl implements Node {

    private String value;
    private int key;
    private Node prev;
    private Node next;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public Node getPrev() {
        return prev;
    }

    @Override
    public void setPrev(Node prev) {
        this.prev = prev;
    }

    @Override
    public Node getNext() {
        return next;
    }

    @Override
    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NodeImpl node = (NodeImpl) o;
        return getKey() == node.getKey()
//                && Objects.equals(getValue(), node.getValue())
                && Objects.equals(getPrev(), node.getPrev())
                && Objects.equals(getNext(), node.getNext());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getPrev(), getNext());
    }
}
