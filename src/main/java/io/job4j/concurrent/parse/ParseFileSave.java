package io.job4j.concurrent.parse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ParseFileSave {
    public void saveContent(String content, File file) {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i++) {
                o.write(content.charAt(i));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}