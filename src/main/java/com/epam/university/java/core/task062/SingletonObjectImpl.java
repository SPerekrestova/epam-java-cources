package com.epam.university.java.core.task062;

import java.io.Serializable;

public class SingletonObjectImpl implements SingletonObject, Serializable {

    private static SingletonObjectImpl instance;

    private SingletonObjectImpl() {
    }

    public static SingletonObjectImpl getInstance() {

        return instance;
    }

    public Object readResolve() {
        return getInstance();
    }
}
