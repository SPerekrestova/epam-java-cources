package com.epam.university.java.core.task060;

import java.util.LinkedList;
import java.util.List;

public class CacheImpl implements Cache {

    private List<Node> nodesList;
    private int size;

    public CacheImpl(int size) {
        nodesList = new LinkedList<>();
        this.size = size;
    }

    @Override
    public String getNode(int key) {
        String result = null;
        for (Node node : nodesList) {
            if (node.getKey() == key) {
                result = node.getValue();
            }
        }
        return result;
    }

    @Override
    public void setNode(int key, String value) {
        if (nodesList.size() == size && nodesList
                .stream()
                .noneMatch(node -> node.getKey() == key)) {
            Node buf = nodesList.get(0);
            int count = 0;
            while (buf.getValue().equals("0") || buf.getValue().contains("new")) {
                count++;
                buf = nodesList.get(count);
            }
            buf.setValue("0");
            nodesList.set(count, buf);
            size++;
            addNewNode(key, value);
        } else if (nodesList.size() == size && nodesList
                .stream()
                .anyMatch(node -> node.getKey() == key)) {
            Node buf = nodesList.stream().filter(e -> e.getKey() == key)
                    .findFirst()
                    .get();
            buf.setValue(value);
            nodesList.set(nodesList.indexOf(buf), buf);
        } else {
            addNewNode(key, value);
        }
    }

    private void addNewNode(int key, String value) {
        Node node = new NodeImpl();
        node.setKey(key);
        node.setValue(value);
        node.setNext(null);
        if (nodesList.isEmpty()) {
            node.setPrev(null);
        } else {
            Node nodePrev = nodesList.get(nodesList.size() - 1);
            nodePrev.setNext(node);
            node.setPrev(nodePrev);
        }
        nodesList.add(node);
    }
}
