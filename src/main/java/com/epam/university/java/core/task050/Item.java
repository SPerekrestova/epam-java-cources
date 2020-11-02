package com.epam.university.java.core.task050;

class Item implements Comparable<Item> {
    final double cost;
    final double weight;
    final double value;

    double getValue() {
        return value;
    }

    Item(double cost, double weight) {
        this.cost = cost;
        this.weight = weight;
        value = cost / weight;
    }

    @Override
    public int compareTo(Item item) {
        return (Double.compare(this.getValue(), item.getValue()));
    }
}