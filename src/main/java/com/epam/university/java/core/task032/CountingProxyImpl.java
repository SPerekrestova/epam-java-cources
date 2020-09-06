package com.epam.university.java.core.task032;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CountingProxyImpl implements CountingProxy {

    private int invocationCountAction = 0;
    private int InvocationCountAnother = 0;

    @Override
    public int getInvocationsCount(String methodName) {
        if ("doAction".equals(methodName)) {
            return invocationCountAction;
        }
        if ("doAnotherAction".equals(methodName)) {
            return InvocationCountAnother;
        }
        return 0;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("doAction".equals(method.getName())) {
            invocationCountAction++;
            return proxy;
        }
        if ("doAnotherAction".equals(method.getName())) {
            InvocationCountAnother++;
            return proxy;
        }
        return null;
    }
}
