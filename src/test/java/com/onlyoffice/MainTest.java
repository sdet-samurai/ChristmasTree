package com.onlyoffice;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    public static String OUTPUT_PATH = "test_output.txt";
    public static Path PATH = Paths.get(OUTPUT_PATH);


    @AfterAll
    public static void cleanup() {

        try {
            Files.deleteIfExists(PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldNotThrowException() {
        // Arrange
        int levels = 4;

        // Act
        assertDoesNotThrow(() -> Main.createChristmasTree(levels, OUTPUT_PATH));

        // Assert
        assertTrue(Files.exists(PATH));
    }

    @Test
    public void shouldPrintTreeWithFourLevels() throws IOException {
        // Arrange
        int levels = 4;
        List<String> expected = """
                             W
                             *
                        @* * * * *
                     * * * * * * * * *@
                @* * * * * * * * * * * * *
                           TTTTT
                           TTTTT""".lines().toList();
        Path path = Paths.get(OUTPUT_PATH);

        // Act
        assertDoesNotThrow(() -> Main.createChristmasTree(levels, OUTPUT_PATH));
        List<String> fileContent = Files.readAllLines(path);


        // Assert
        assertLinesMatch(expected, fileContent);
    }

    @Test
    public void shouldPrintTreeWithFiveLevels() throws IOException {
        // Arrange
        int levels = 5;
        List<String> expected = """
                                W
                                *
                           @* * * * *
                        * * * * * * * * *@
                   @* * * * * * * * * * * * *
                * * * * * * * * * * * * * * * * *@
                              TTTTT
                              TTTTT""".lines().toList();
        Path path = Paths.get(OUTPUT_PATH);

        // Act
        assertDoesNotThrow(() -> Main.createChristmasTree(levels, OUTPUT_PATH));
        List<String> fileContent = Files.readAllLines(path);


        // Assert
        assertLinesMatch(expected, fileContent);
    }
}