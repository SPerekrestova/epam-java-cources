package com.epam.university.java.core.task056;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Task056Impl implements Task056 {

    private List<Pair> prescriptionListOriginal;

    @Override
    public Collection<Integer> necessaryMedication(String prescriptionFile) {
        if (prescriptionFile == null) {
            throw new IllegalArgumentException();
        }
        prescriptionListOriginal = readFile(prescriptionFile);
        List<Pair> prescriptionList = readFile(prescriptionFile);

        Collections.sort(prescriptionList);
        List<Pair> resultList = new ArrayList<>();
        resultList.add(prescriptionList.get(0));
        for (int i = 1; i < prescriptionList.size(); i++) {
            if (prescriptionList.get(i).getValue().left
                    > resultList.get(resultList.size() - 1).getValue().right) {
                resultList.add(prescriptionList.get(i));
            }
        }
        List<Integer> answer = new LinkedList<>();

        for (Pair pair : resultList) {
            answer.add(prescriptionListOriginal.indexOf(pair));
        }
        Collections.sort(answer);
        return answer;
    }

    @Override
    public Collection<String> intervalBetweenMedication(Collection<Integer> necessaryMedication) {
        /*if (necessaryMedication == null) {
            throw new IllegalArgumentException();
        }
        if (necessaryMedication.size() == 1) {
            return Collections.emptyList();
        }
        List<Integer> medication = (List<Integer>) necessaryMedication;
        List<String> resultList = new LinkedList<>();
        for (int i = 1; i < necessaryMedication.size(); i++) {
            int first = prescriptionListOriginal.get(medication.get(i)).getValue().left
            int count = prescriptionListOriginal.get(medication.get(i)).getValue().left
                     prescriptionListOriginal.get(medication.get(i - 1)).getValue().right;
            if (count > 1) {
                StringBuilder sb = new StringBuilder();

            }
        }*/

        return null;
    }

    private List<Pair> readFile(String prescriptionFile) {
        List<Pair> prescriptionList = new LinkedList<>();
        try {
            List<String> allLines = Files.readAllLines(
                    Paths.get(
                            prescriptionFile.replaceFirst("/", "")
                    )
            );
            for (String line : allLines) {
                int packs = Integer.parseInt(line.substring(0, 2)
                        .trim());
                int left = Integer.parseInt(line.substring(2, 4)
                        .trim().replace("-", ""));
                int right = Integer.parseInt(line.substring(line.indexOf("-"))
                        .trim().replace("-", ""));
                IntPair intPair = new IntPair(left, right);
                Pair pair = new Pair(packs, intPair);
                prescriptionList.add(pair);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prescriptionList;
    }
}
