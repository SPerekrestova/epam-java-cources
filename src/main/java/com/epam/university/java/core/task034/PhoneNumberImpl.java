package com.epam.university.java.core.task034;

import java.io.Serializable;

public class PhoneNumberImpl implements PhoneNumber, Serializable {
    private static final long serialVersionUID = 1L;
    private String phoneNumber;

    public PhoneNumberImpl() {
        super();
    }

    public PhoneNumberImpl(String phoneNumber) {
        super();
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
