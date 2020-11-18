package com.epam.university.java.core.task057;

import java.util.ArrayList;
import java.util.List;

public class Task057Impl implements Task057 {

    int currEntrance;

    @Override
    public Window getWindowForDelivery(int level, int entrances, int numberOfFlat) {
        if (level == 0 || entrances == 0 || numberOfFlat == 0
                || numberOfFlat > (level * entrances * 8)) {
            throw new IllegalArgumentException();
        }

        int levelNumber = countFloor(numberOfFlat, level);
        SideType side = calculateSide(numberOfFlat);
        int numberOfWindow = calculateWindow(entrances, numberOfFlat, side);

        return new WindowImpl(levelNumber, numberOfWindow, side);
    }

    private int calculateWindow(int entrances, int numberOfFlat, SideType sideType) {
        int result;
        while (numberOfFlat > 8) {
            numberOfFlat -= 8;
        }
        if (sideType.equals(SideType.FRONT_SIDE)) {
            result = (currEntrance - 1) * 8;
        } else {
            result = (entrances - currEntrance) * 8;
        }
        switch (numberOfFlat) {
            case 1:
            case 5:
                result += 6;
                break;
            case 2:
            case 6:
                result += 8;
                break;
            case 8:
            case 4:
                result += 4;
                break;
            case 7:
            case 3:
                result += 2;
                break;
            default:
                break;
        }
        return result;
    }

    private int countFloor(int numberOfFlat, int level) {
        int flatsInEachEntrance = level * 8;
        int buf = flatsInEachEntrance;
        currEntrance = 1;
        while (numberOfFlat >= buf) {
            buf += flatsInEachEntrance;
            currEntrance++;
        }
        while (numberOfFlat <= buf) {
            level--;
            buf -= 8;
        }
        return level + 1;
    }

    private SideType calculateSide(int numberOfFlat) {
        List<Integer> backList = new ArrayList<>(List.of(6, 5, 4, 3));
        SideType sideType;
        while (numberOfFlat > 8) {
            numberOfFlat -= 8;
        }
        if (backList.contains(numberOfFlat)) {
            sideType = SideType.BACK_SIDE;
        } else {
            sideType = SideType.FRONT_SIDE;
        }
        return sideType;
    }
}
