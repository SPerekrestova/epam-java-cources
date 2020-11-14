package com.epam.university.java.core.task055;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dataset")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dataset {

    @XmlElement(name = "passports_houses", type = HouseDefinitionImpl.class)
    private List<HouseDefinition> houseDefinitionList = new ArrayList<>();

    public List<HouseDefinition> getHouseDefinitionList() {
        return houseDefinitionList;
    }

    public void setLisOfBeans(List<HouseDefinition> list) {
        this.houseDefinitionList = list;
    }

}
