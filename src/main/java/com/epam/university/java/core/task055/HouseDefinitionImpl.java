package com.epam.university.java.core.task055;

import java.util.Objects;

public class HouseDefinitionImpl implements HouseDefinition {

    private String address;
    private int year;
    private double area;

    //addr_district ??

    //address
    @Override
    public String getAddress() {
        return address;
    }
    //data_buildingdate
    @Override
    public int getYear() {
        return year;
    }
    //data_buildingarea
    @Override
    public double getArea() {
        return area;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HouseDefinitionImpl that = (HouseDefinitionImpl) o;
        return year == that.year
                && Double.compare(that.area, area) == 0
                && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, year, area);
    }
}
