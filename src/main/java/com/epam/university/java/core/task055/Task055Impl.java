package com.epam.university.java.core.task055;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Task055Impl implements Task055 {

    @Override
    public ProcessingContext createContext(String path) {
        JAXBContext jaxbContext;
        HouseDefinitionImpl houseDefinition = null;
        try {
            jaxbContext = JAXBContext.newInstance(HouseDefinitionImpl.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            houseDefinition = (HouseDefinitionImpl) unmarshaller.unmarshal(new File("src/main/resources" + path));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
