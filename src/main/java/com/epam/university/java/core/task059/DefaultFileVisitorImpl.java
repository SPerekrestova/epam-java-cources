package com.epam.university.java.core.task059;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class DefaultFileVisitorImpl extends DefaultFileVisitor {
    private final List<String> foundFiles = new ArrayList<>();

    private String partOfContent;
    private boolean partOfContentCheck;

    @Override
    public List<String> getResult() {
        return foundFiles;
    }

    /**
       Default constructor.
     */
    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
        this.partOfContentCheck = true;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (partOfContentCheck && new String(Files.readAllBytes(file)).contains(partOfContent)) {
            foundFiles.add(String.valueOf(file));
        }
        return FileVisitResult.CONTINUE;
    }
}
