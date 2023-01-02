package com.jwe.util;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Stack;

public class DataLoader {
    private final String src;
    private final Stack<String> data = new Stack<>();

    public DataLoader(String src) {
        this.src = src;
    }

    public Stack<String> getLoadedData() throws IOException {
        File dir = new File(src);
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isFile()) {
                data.push(IOUtils.toString(file.toURI(), StandardCharsets.UTF_8));
            }
        }
        return data;
    }
}