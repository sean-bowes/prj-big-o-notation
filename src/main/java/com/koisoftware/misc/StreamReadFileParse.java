package com.koisoftware.misc;

import java.io.File;
import java.nio.file.Files;
import java.util.stream.Stream;

public class StreamReadFileParse {
    static class data {
        String d1;
        String d2;
        String d3;

        public data(String d1, String d2, String d3) {
            this.d1 = d1;
            this.d2 = d2;
            this.d3 = d3;
        }
    }

    public static void main(String args[]) throws Exception {
        String fileName = "c:\\temp\\t.txt";
        File file = new File(fileName);

//        try (Stream<String> linesStream = Files.lines(file.toPath())) {
//            linesStream.forEach(line -> {
//                System.out.println(line);
//            });
//        }

        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            linesStream.forEach(line -> processLine(line));
        }
        System.out.println("Max=" + maxCnt);

//        List<data> list = new ArrayList<>();
//        try (Stream<String> lineStream = Files.lines(file.toPath())) {
//            list = lineStream
//                    .map(line -> line.split("\\|"))
//                    .map(arr -> new data(arr[0], arr[1], arr[2]))
//                    .collect(Collectors.toList());
//
//        }
//        for (data d: list) {
//            System.out.println(d.d1+","+d.d2+","+d.d3);
//        }

    }

    //hello sean,... hello
    static int maxCnt = 0;

    public static void processLine(String line) {
        String[] t = line.split("\\|");
        if (maxCnt < Integer.parseInt(t[2])) {
            maxCnt = Integer.parseInt(t[2]);
        }
    }
}
