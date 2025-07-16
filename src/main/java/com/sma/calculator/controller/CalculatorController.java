package com.sma.calculator.controller;

import com.sma.calculator.dto.CalculationResponse;
import com.sma.calculator.dto.ErrorResponse;
import com.sma.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final CalculatorService calculator;

    @Autowired
    public CalculatorController(CalculatorService calculator) {
        this.calculator = calculator;
    }

    @GetMapping("/add")
    public ResponseEntity<CalculationResponse> add(
            @RequestParam int a,
            @RequestParam int b) {
        int result = calculator.add(a, b);
        return ResponseEntity.ok(new CalculationResponse(
                result,
                "addition",
                String.format("%d + %d", a, b)
        ));
    }

    @GetMapping("/subtract")
    public ResponseEntity<CalculationResponse> subtract(
            @RequestParam int a,
            @RequestParam int b) {
        int result = calculator.subtract(a, b);
        return ResponseEntity.ok(new CalculationResponse(
                result,
                "subtraction",
                String.format("%d - %d", a, b)
        ));
    }

    @GetMapping("/multiply")
    public ResponseEntity<CalculationResponse> multiply(
            @RequestParam int a,
            @RequestParam int b) {
        int result = calculator.multiply(a, b);
        return ResponseEntity.ok(new CalculationResponse(
                result,
                "multiplication",
                String.format("%d * %d", a, b)
        ));
    }

    @GetMapping("/divide")
    public ResponseEntity<CalculationResponse> divide(
            @RequestParam int a,
            @RequestParam int b) {
        double result = calculator.divide(a, b);
        return ResponseEntity.ok(new CalculationResponse(
                result,
                "division",
                String.format("%d / %d", a, b)
        ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getMessage(), "Invalid Input"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        return ResponseEntity
                .internalServerError()
                .body(new ErrorResponse("An unexpected error occurred", e.getMessage()));
    }
}
