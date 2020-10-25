package com.epam.university.java.project.core.state.machine.domain;

public class StateMachineStateImpl implements StateMachineState {
    private Object from;
    private Object to;
    private Object on;
    private String methodToCall;

    @Override
    public Object getFrom() {
        return from;
    }

    @Override
    public void setFrom(Object state) {
        this.from = state;
    }

    @Override
    public Object getTo() {
        return to;
    }

    @Override
    public void setTo(Object state) {
        this.to = state;
    }

    @Override
    public Object getOn() {
        return on;
    }

    @Override
    public void setOn(Object o) {
        this.on = o;
    }

    @Override
    public String getMethodToCall() {
        return methodToCall;
    }

    @Override
    public void setMethodToCall(String method) {
        this.methodToCall = method;
    }
}
