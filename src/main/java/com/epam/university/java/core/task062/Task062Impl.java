package com.epam.university.java.core.task062;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Task062Impl implements Task062 {

    @Override
    public OutputStream objectSerialization(Object obj) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream
                = null;
        try {
            fileOutputStream = new FileOutputStream("file.txt");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objectOutputStream;
    }

    @Override
    public Object objectDeserialization(OutputStream outStream) {
        Object obj = null;
        try {
            FileInputStream fileInputStream
                    = new FileInputStream("file.txt");
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream);
            obj = objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
