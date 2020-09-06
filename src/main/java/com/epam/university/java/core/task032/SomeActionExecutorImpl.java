package com.epam.university.java.core.task032;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SomeActionExecutorImpl implements SomeActionExecutor {

    private int invocationCountAction = 0;
    private int getInvocationCountAnother = 0;
    SomeActionExecutorImpl account;

    public SomeActionExecutorImpl(CountingProxy proxy) {
        account = (SomeActionExecutorImpl) Proxy
                .newProxyInstance(
                        getClass()
                                .getClassLoader(),
                        new Class[] {
                                CountingProxyImpl.class,
                                Serializable.class
                        },
                new CountingProxyImpl()
                );
    }

    @Override
    public void doAction() {
        Method method = null;
        try {
            method = this.getClass().getDeclaredMethod("doAction");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        this.invocationCountAction++;
    }

    @Override
    public void doAnotherAction() {
        this.getInvocationCountAnother++;
    }
}
