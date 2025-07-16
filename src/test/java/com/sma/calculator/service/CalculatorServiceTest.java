package com.sma.calculator.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CalculatorServiceTest {

    @Autowired
    private CalculatorService calculatorService;

    @Test
    @DisplayName("Addition should work correctly")
    void addShouldReturnSum() {
        // Given
        int a = 5;
        int b = 3;
        
        // When
        int result = calculatorService.add(a, b);
        
        // Then
        assertEquals(8, result, "5 + 3 should equal 8");
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
        "0, 1, 1",
        "1, 2, 3",
        "-1, 1, 0",
        "10, -5, 5",
        "-3, -7, -10"
    })
    @DisplayName("Addition should work with various inputs")
    void addShouldWorkWithVariousInputs(int a, int b, int expectedResult) {
        assertEquals(expectedResult, calculatorService.add(a, b),
            () -> a + " + " + b + " should equal " + expectedResult);
    }

    @Test
    @DisplayName("Subtraction should work correctly")
    void subtractShouldReturnDifference() {
        // Given
        int a = 8;
        int b = 3;
        
        // When
        int result = calculatorService.subtract(a, b);
        
        // Then
        assertEquals(5, result, "8 - 3 should equal 5");
    }

    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
        "1, 1, 0",
        "5, 2, 3",
        "-1, -1, 0",
        "10, -5, 15",
        "-3, 7, -10"
    })
    @DisplayName("Subtraction should work with various inputs")
    void subtractShouldWorkWithVariousInputs(int a, int b, int expectedResult) {
        assertEquals(expectedResult, calculatorService.subtract(a, b),
            () -> a + " - " + b + " should equal " + expectedResult);
    }

    @Test
    @DisplayName("Multiplication should work correctly")
    void multiplyShouldReturnProduct() {
        // Given
        int a = 4;
        int b = 5;
        
        // When
        int result = calculatorService.multiply(a, b);
        
        // Then
        assertEquals(20, result, "4 * 5 should equal 20");
    }

    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({
        "0, 1, 0",
        "1, 1, 1",
        "-2, 3, -6",
        "10, -5, -50",
        "-3, -3, 9"
    })
    @DisplayName("Multiplication should work with various inputs")
    void multiplyShouldWorkWithVariousInputs(int a, int b, int expectedResult) {
        assertEquals(expectedResult, calculatorService.multiply(a, b),
            () -> a + " * " + b + " should equal " + expectedResult);
    }

    @Test
    @DisplayName("Division should work correctly")
    void divideShouldReturnQuotient() {
        // Given
        int a = 10;
        int b = 2;
        
        // When
        double result = calculatorService.divide(a, b);
        
        // Then
        assertEquals(5.0, result, 0.0001, "10 / 2 should equal 5.0");
    }

    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
        "10, 2, 5.0",
        "15, 3, 5.0",
        "-6, 2, -3.0",
        "8, -4, -2.0",
        "-12, -4, 3.0"
    })
    @DisplayName("Division should work with various inputs")
    void divideShouldWorkWithVariousInputs(int a, int b, double expectedResult) {
        assertEquals(expectedResult, calculatorService.divide(a, b), 0.0001,
            () -> a + " / " + b + " should equal " + expectedResult);
    }

    @Test
    @DisplayName("Division by zero should throw IllegalArgumentException")
    void divideShouldThrowExceptionWhenDividingByZero() {
        // Given
        int a = 10;
        int b = 0;
        
        // When & Then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calculatorService.divide(a, b));
        assertEquals("Division by zero is not allowed.", exception.getMessage());
    }
}
