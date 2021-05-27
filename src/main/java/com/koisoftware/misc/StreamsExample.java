package com.koisoftware.misc;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamsExample {
    public static void main(String[] args) throws IOException {
        Stream<Path> path = Files.walk(Paths.get("C:\\Temp"));
        System.out.println("List of PDF files:");
        path = path.filter(var -> var.toString().endsWith(".jpg"));
        path.forEach(System.out::println);

        //Creating a File object for directory
        File directoryPath = new File("C:\\Temp");
        //Creating filter for jpg files
        FilenameFilter jpgFilefilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".jpg")) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        String imageFilesList[] = directoryPath.list(jpgFilefilter);
        System.out.println("1. List of the jpeg files in the specified directory:");
        for (String fileName : imageFilesList) {
            System.out.println(fileName);
        }

        File dir = new File("C:\\Temp");
        String[] list = dir.list();
        System.out.println(Arrays.toString(list));
        boolean flag = false;
        System.out.println("2. List of the jpeg files in the specified directory:");
        for (int i = 0; i < list.length; i++) {
            if (list[i].toLowerCase().endsWith(".jpg")) {
                System.out.println(list[i]);
            }
        }
    }
}
