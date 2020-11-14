package com.epam.university.java.core.task055;

import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessingContextImpl implements ProcessingContext {

    private final Collection<HouseDefinition> houseDefinitions;

    public ProcessingContextImpl(Collection<HouseDefinition> houseDefinitions) {
        this.houseDefinitions =  houseDefinitions;
    }

    @Override
    public Collection<HouseDefinition> oldestHouses() {
        int minYear = Integer.MAX_VALUE;
        for (HouseDefinition house : houseDefinitions) {
            if (house.getYear() < minYear && house.getYear() > 0) {
                minYear = house.getYear();
            }
        }
        Collection<HouseDefinition> result = new LinkedList<>();
        for (HouseDefinition house : houseDefinitions) {
            if (house.getYear() == minYear) {
                result.add(house);
            }
        }
        return result;
    }

    @Override
    public int averageAge(String district) {
        int sumOfAge = 0;
        int count = 0;
        int currentAge = 2020;
        for (HouseDefinition house : houseDefinitions) {
            if (district.equals(((HouseDefinitionImpl) house).getDistrict())
                    || district.equals("Город")) {
                Pattern pattern = Pattern.compile("\\d{4}");
                Matcher matcher = pattern.matcher(String.valueOf(house.getYear()));
                while (matcher.find()) {
                    currentAge = Integer.parseInt(matcher.group());
                }
                sumOfAge += 2020 - currentAge;
                count++;
            }
        }
        return sumOfAge / count;
    }

    @Override
    public HouseDefinition biggestTotalFloorSpace() {
        double max = Double.MIN_VALUE;
        for (HouseDefinition house : houseDefinitions) {
            if (house.getArea() > max) {
                max = house.getArea();
            }
        }
        HouseDefinition result = null;
        for (HouseDefinition house : houseDefinitions) {
            if (house.getArea() == max) {
                result = house;
            }
        }
        return result;
    }

    @Override
    public HouseDefinition smallestTotalFloorSpace() {
        double min = Double.MAX_VALUE;
        for (HouseDefinition house : houseDefinitions) {
            if (house.getArea() < min && house.getArea() > 0) {
                min = house.getArea();
            }
        }
        HouseDefinition result = null;
        for (HouseDefinition house : houseDefinitions) {
            if (house.getArea() == min) {
                result = house;
            }
        }
        return result;
    }

    @Override
    public int totalCountHouses() {
        return houseDefinitions.size();
    }

    @Override
    public int totalCountHousesWithCommunalFlats() {
        int count = 0;
        for (HouseDefinition house : houseDefinitions) {
            if (!((HouseDefinitionImpl) house).getCommunalFlats().isBlank()) {
                count++;
            }
        }
        return count;
    }
}
