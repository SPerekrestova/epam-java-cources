package com.epam.university.java.core.task059;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Task059Impl implements Task059 {
    @Override
    public List<String> find(String path, String substring) {
        if (path == null || substring == null) {
            throw new IllegalArgumentException();
        }
        DefaultFileVisitorImpl defaultFileVisitor = new DefaultFileVisitorImpl();
        defaultFileVisitor.setPartOfContent(substring);
        try {
            Files.walkFileTree(Paths.get(path), defaultFileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defaultFileVisitor.getResult();
    }
}
