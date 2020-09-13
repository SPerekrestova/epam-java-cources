package com.epam.university.java.core.task037;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Task037Impl implements Task037 {
    @Override
    public Collection<String> switcher(Callable<String> ticker, Callable<String> tacker) {
        if (ticker == null && tacker == null) {
            throw new IllegalArgumentException();
        }
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ArrayList<String> list = new ArrayList<>();
        assert ticker != null;
        Future<String> futureTicker = executor.submit(ticker);
        Future<String> futureTacker = executor.submit(tacker);

        while (list.size() != 10) {
            try {
                list.add(futureTicker.get());
                list.add(futureTacker.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
