package ru.nsu.babich;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BlackjackTest {
    private Blackjack game;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        game = new Blackjack();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void checkMain() {
        String input = "0\n0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Blackjack.main(new String[]{});

        String output = outputStream.toString();
        assertTrue(output.contains("Добро пожаловать в Блэкджек!"));
    }

}