package io.job4j.concurrent.parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (InputStream input = new FileInputStream(file)) {
            int data;
            while ((data = input.read()) > 0) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
            return output.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getContent() {
        return content((data) -> true);
    }

    public String getContentWithoutUnicode() {
        return content((data) -> data < 0x80);
    }
}