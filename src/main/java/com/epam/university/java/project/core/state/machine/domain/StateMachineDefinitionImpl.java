package com.epam.university.java.project.core.state.machine.domain;

import java.util.ArrayList;
import java.util.Collection;

public class StateMachineDefinitionImpl implements StateMachineDefinition {
    private Object startState;
    private Object startEvent;
    private final Collection<StateMachineState> statesCollection = new ArrayList<>();
    private Class handlerClass;

    @Override
    public Object getStartEvent() {
        return startEvent;
    }

    @Override
    public Object getStartState() {
        return startState;
    }

    @Override
    public void setStartEvent(Object o) {
        this.startEvent = o;
    }

    @Override
    public void setStartState(Object o) {
        this.startState = o;
    }

    @Override
    public Collection<StateMachineState> getStates() {
        return statesCollection;
    }

    @Override
    public void addState(StateMachineState state) {
        statesCollection.add(state);
    }

    @Override
    public Class<? extends StateMachineEventHandler> getHandlerClass() {
        return handlerClass;
    }

    @Override
    public void setHandlerClass(Class handlerClass) {
        this.handlerClass = handlerClass;
    }
}
