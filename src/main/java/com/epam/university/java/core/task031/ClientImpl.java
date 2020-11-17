package com.epam.university.java.core.task031;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientImpl implements Client {

    private Socket clientSocket;
    private PrintWriter out;
    private final int port;
    boolean isNull;

    public ClientImpl(int port) {
        this.port = port;
    }

    @Override
    public void sendMessage(String message) {
        if (message == null) {
            isNull = true;
        }
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(message);
            out.flush();
            Thread.sleep(100);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        try {
            Thread.sleep(100);
            clientSocket = new Socket(InetAddress.getLocalHost(), port);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            clientSocket.close();
            out.close();
            if (isNull) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
