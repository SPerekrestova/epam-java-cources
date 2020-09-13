package com.epam.university.java.core.task035;

import com.epam.university.java.core.task034.Person;
import com.epam.university.java.core.task034.PersonImpl;
import com.epam.university.java.core.task034.PhoneNumber;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class Task035Impl implements Task035 {

    @Override
    public Person readWithJackson(ObjectMapper mapper, String jsonString) {
        Person person = null;
        try {
            person = mapper.readValue(jsonString, PersonImpl.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Person readWithGson(GsonBuilder builder, String jsonString) {
        builder.registerTypeAdapter(PhoneNumber.class, new PersonDeserializer<>());
        Gson gson = builder.create();
        PersonImpl person = gson.fromJson(jsonString, PersonImpl.class);

        return person;
    }
}
