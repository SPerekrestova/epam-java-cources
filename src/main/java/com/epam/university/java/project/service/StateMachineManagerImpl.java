package com.epam.university.java.project.service;

import com.epam.university.java.project.core.cdi.io.Resource;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinitionImpl;
import com.epam.university.java.project.core.state.machine.domain.StateMachineState;
import com.epam.university.java.project.core.state.machine.domain.StatefulEntity;
import com.epam.university.java.project.core.state.machine.manager.StateMachineManager;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class StateMachineManagerImpl implements StateMachineManager {

    @Override
    public StateMachineDefinition<?, ?> loadDefinition(Resource resource) {
        JAXBContext jaxbContext;
        StateMachineDefinitionImpl definition = null;
        try {
            jaxbContext = JAXBContext.newInstance(StateMachineDefinitionImpl.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            definition = (StateMachineDefinitionImpl) unmarshaller.unmarshal(resource.getFile());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return definition;
    }

    @Override
    public <S, E> StatefulEntity<S, E> initStateMachine(StatefulEntity<S, E> entity,
                                                        StateMachineDefinition<S, E> definition) {
        entity.setStateMachineDefinition(definition);
        entity.setState(definition.getStartState());
        return entity;
    }

    @Override
    public <S, E> StatefulEntity<S, E> handleEvent(StatefulEntity<S, E> entity, E event) {
        StateMachineDefinitionImpl stateMachineDefinition = new StateMachineDefinitionImpl();
        for (StateMachineState state : stateMachineDefinition.getStates()) {
            if (state.getOn().equals(entity.getState())) {

            }
        }
        return null;
    }
}
