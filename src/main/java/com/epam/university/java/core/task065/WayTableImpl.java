package com.epam.university.java.core.task065;

import java.time.LocalDate;
import java.util.Map;

public class WayTableImpl implements WayTable {

    private final Map<LocalDate, Double> tableMap;
    private final int countWays;

    public WayTableImpl(Map<LocalDate, Double> tableMap, int countWays) {
        this.tableMap = tableMap;
        this.countWays = countWays;
    }

    @Override
    public int getDistOfDate(LocalDate localDate) {
        return (int) Math.round(tableMap.get(localDate));
    }

    @Override
    public int getAllDistance() {
        double count = 0;
        for (Double i : tableMap.values()) {
            count += i;
        }
        return (int) Math.round(count);
    }

    @Override
    public int getCountWays() {
        return countWays;
    }
}
