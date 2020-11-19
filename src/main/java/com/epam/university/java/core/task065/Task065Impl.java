package com.epam.university.java.core.task065;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Task065Impl implements Task065 {

    @Override
    public WayTable getWayTable(String filepath) {
        if (filepath == null || filepath.isBlank()) {
            throw new IllegalArgumentException();
        }
        String content = "";
        try {
            content = new String(Files.readAllBytes(
                    Path.of(getClass().getResource(filepath)
                                      .toURI()))
            );
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        Document doc = Jsoup.parse(content);

        Elements listOfRows = doc.select("div#user_void.heder_bloc_glav")
                                 .get(0)
                                 .getElementsByTag("table")
                                 .get(0)
                                 .getElementsByTag("tbody")
                                 .get(1)
                                 .getElementsByTag("tr");

        Map<LocalDate, Double> tableMap = new HashMap<>();
        int countWays = 0;
        for (Element element : listOfRows) {
            if (!element.child(0)
                        .text()
                        .isBlank()) {
                countWays++;
                Element dateEl = element.child(2);
                String date = dateEl.text();
                LocalDate localDate = LocalDate.parse(date, formatter);
                double distance;
                if (element.child(7)
                           .text()
                           .isBlank()) {
                    Element distanceEl = element.child(5);
                    distance = Double.parseDouble(distanceEl.text());
                } else {
                    Element distanceEl = element.child(7);
                    distance = Double.parseDouble(distanceEl.text()
                                                            .replaceAll("\\D+", ""));
                }
                if (tableMap.containsKey(localDate)) {
                    double buf = tableMap.get(localDate);
                    tableMap.replace(localDate, distance + buf);
                } else {
                    tableMap.put(localDate, distance);
                }
            }
        }

        return new WayTableImpl(tableMap, countWays);
    }
}
