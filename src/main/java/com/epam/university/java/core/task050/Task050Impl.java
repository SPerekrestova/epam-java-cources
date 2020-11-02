package com.epam.university.java.core.task050;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Task050Impl implements Task050 {
    @Override
    public double calculate(int size, Map<Double, Double> items) {
        if (size == 0 || items == null) {
            throw new IllegalArgumentException();
        }
        List<Item> itemsList = new ArrayList<>();
        Item item;
        for (Double aDouble : items.keySet()) {
            item = new Item(aDouble, items.get(aDouble));
            itemsList.add(item);
        }
        itemsList.sort(Collections.reverseOrder());
        List<Double> backpack = new ArrayList<>();
        int index = 0;
        while (size > 0 && itemsList.size() > index) {
            if (itemsList.get(index).weight <= size) {
                backpack.add(itemsList.get(index).cost);
                size -= itemsList.get(index).weight;
                index++;
            } else if (itemsList.get(index).weight > 0) {
                double part = itemsList.get(index).weight - (itemsList.get(index).weight - size);
                double cost = part * itemsList.get(index).value;
                backpack.add(cost);
                size -= part;
                index++;
            } else {
                break;
            }
        }
        double result = 0f;
        for (Double aDouble : backpack) {
            result += aDouble;
        }
        BigDecimal value = new BigDecimal(result);
        value = value.setScale(3, RoundingMode.HALF_UP);

        return value.doubleValue();
    }
}