package com.avis.datautils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileReader {


    public static String readFile(String path) {
        File file = new File(path);
        try {
            return FileUtils.readFileToString(file,StandardCharsets.UTF_8);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
