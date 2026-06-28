package com.loskutnikov.ci_cd.domain;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalcTest {

    @Test
    void summ_shouldPrintCorrectSum() {
        // given
        Calc yourClass = new Calc();
        int a = 5;
        int b = 3;
        String expectedOutput = "Summa= 7" + System.lineSeparator();

        // Перехватываем вывод в консоль
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            // when
            yourClass.summ(a, b);

            // then
            assertEquals(expectedOutput, outContent.toString());
        } finally {
            // Возвращаем стандартный вывод
            System.setOut(originalOut);
        }
    }

    @Test
    void summ_shouldPrintCorrectSumForNegativeNumbers() {
        // given
        Calc yourClass = new Calc();
        int a = -5;
        int b = 3;
        String expectedOutput = "Summa= -2" + System.lineSeparator();

        // Перехватываем вывод в консоль
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            // when
            yourClass.summ(a, b);

            // then
            assertEquals(expectedOutput, outContent.toString());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void summ_shouldPrintCorrectSumForZero() {
        // given
        Calc yourClass = new Calc();
        int a = 0;
        int b = 0;
        String expectedOutput = "Summa= 0" + System.lineSeparator();

        // Перехватываем вывод в консоль
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            // when
            yourClass.summ(a, b);

            // then
            assertEquals(expectedOutput, outContent.toString());
        } finally {
            System.setOut(originalOut);
        }
    }
}
