package com.epam.university.java.core.task055;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "passports_houses")
@XmlAccessorType(XmlAccessType.FIELD)
public class HouseDefinitionImpl implements HouseDefinition {

    @XmlElement(name = "address")
    private String address;

    @XmlElement(name = "data_buildingdate")
    private int year;

    @XmlElement(name = "data_buildingarea")
    private double area;

    @XmlElement(name = "addr_district")
    private String district;

    @XmlElement(name = "comm_type")
    private String communalFlats;

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public double getArea() {
        return area;
    }

    public String getDistrict() {
        return district;
    }

    public String getCommunalFlats() {
        return communalFlats;
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

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCommunalFlats(String communalFlats) {
        this.communalFlats = communalFlats;
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
