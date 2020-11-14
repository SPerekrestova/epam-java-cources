package com.epam.university.java.core.task055;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Task055Impl implements Task055 {

    @Override
    public ProcessingContext createContext(String path) {
        JAXBContext jaxbContext;
        Dataset dataset;
        List<HouseDefinition> houseDefinitionList = null;
        try {
            jaxbContext = JAXBContext.newInstance(Dataset.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            dataset = (Dataset) unmarshaller.unmarshal(new File("src/main/resources" + path));
            houseDefinitionList = dataset.getHouseDefinitionList();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return new ProcessingContextImpl(houseDefinitionList);
    }
}
