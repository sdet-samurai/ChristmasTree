package com.onlyoffice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Main {
    public static void main(String[] args) {
        int levels = Integer.parseInt(new AppConfig("config.properties").getProperty("levels"));
        String outputPath = "output/output.txt";

        try {
            createChristmasTree(levels, outputPath);
            System.out.println("Ёлка успешно создана в файле: " + outputPath);
        } catch (IOException e) {
            System.err.println("Ошибка при записи файла: " + e.getMessage());
        }
    }

    public static void createChristmasTree(int levels, String outputPath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            if (levels % 2 == 0) {
                createChristmasTreeWithEvenLevels(levels, writer);
            } else {
                createChristmasTreeWithOddLevels(levels, writer);
            }
        }
    }

    private static void createChristmasTreeWithEvenLevels(int levels, Writer writer) throws IOException {
        int stars = 1;
        writer.write(" ".repeat((levels - 1) * 4 + 1) + "W\n");
        writer.write(" ".repeat((levels - 1) * 4 + 1) + "*\n");
        for (int current = 2; current <= levels; current++) {
            stars += 4;
            if (current % 2 == 0) {
                writer.write(" ".repeat((levels - current) * 4));
                writer.write("@" + "* ".repeat(stars - 1) + "*" + "\n");
            } else {
                writer.write(" ".repeat((levels - current) * 4 + 1));
                writer.write("* ".repeat(stars - 1) + "*@\n");
            }

        }
        String trunk = " ".repeat((levels - 1) * 4 + 1 - levels / 2)
                + "T".repeat(levels + 1)
                + "\n";
        writer.write(trunk);
        writer.write(trunk);
    }

    private static void createChristmasTreeWithOddLevels(int levels, Writer writer) throws IOException {
        int stars = 1;
        writer.write(" ".repeat((levels - 1) * 4) + "W\n");
        writer.write(" ".repeat((levels - 1) * 4) + "*\n");
        for (int current = 2; current <= levels; current++) {
            stars += 4;
            if (current % 2 == 0) {
                writer.write(" ".repeat((levels - current) * 4 - 1));
                writer.write("@" + "* ".repeat(stars - 1) + "*" + "\n");
            } else {
                writer.write(" ".repeat((levels - current) * 4));
                writer.write("* ".repeat(stars - 1) + "*@\n");
            }

        }
        String trunk = " ".repeat((levels - 1) * 4 - levels / 2)
                + "T".repeat(levels)
                + "\n";
        writer.write(trunk);
        writer.write(trunk);
    }
}
