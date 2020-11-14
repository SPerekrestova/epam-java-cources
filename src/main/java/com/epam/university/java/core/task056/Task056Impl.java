package com.epam.university.java.core.task056;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task056Impl implements Task056 {

    @Override
    public Collection<Integer> necessaryMedication(String prescriptionFile) {
        if (prescriptionFile == null) {
            throw new IllegalArgumentException();
        }
        Collection<Pair> prescriptionList = readFile(prescriptionFile);

        //apply approach from 4.1 Stepik

        return null;
    }

    private Collection<Pair> readFile(String prescriptionFile) {
        Collection<Pair> prescriptionList = new LinkedList<>();
        List<Integer> period;
        try {
            List<String> allLines = Files.readAllLines(
                    Paths.get(
                            prescriptionFile.replaceFirst("/", "")
                    )
            );
            for (String line : allLines) {
                int packs = Integer.parseInt(line.substring(0, 2)
                                                 .trim());
                period = new LinkedList<>();
                Pattern p = Pattern.compile("\\s+\\d+|\\d+$");
                Matcher m = p.matcher(line);
                while (m.find()) {
                    int n = Integer.parseInt(m.group()
                                              .trim());
                    period.add(n);
                }
                Pair pair = new Pair(packs, period);
                prescriptionList.add(pair);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prescriptionList;
    }

    @Override
    public Collection<String> intervalBetweenMedication(Collection<Integer> necessaryMedication) {
        if (necessaryMedication == null) {
            throw new IllegalArgumentException();
        }
        if (necessaryMedication.size() == 1) {
            return Collections.emptyList();
        }
        return null;
    }
}
