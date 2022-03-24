package com.tms.calculatorWebApp.model;

import com.tms.calculatorWebApp.servlet.ServletCalculator;

public abstract class Calculator {

    private Calculator() {
    }

    private static double addition(double num1, double num2) {
        return num1 + num2;
    }
     
    private static double subtraction(double num1, double num2) {
        return num1 - num2;
    }

    private static double multiplication(double num1, double num2) {
        return num1 * num2;
    }

    private static double division(double num1, double num2) {
        if (num2 == 0) {
            ServletCalculator.bugReport = "Division by zero is not allowed";
            return 0;
        }
        return num1 / num2;
    }

    public static double performOperation(double num1, double num2, String operation) {
        switch (operation) {
            case "add":
                ServletCalculator.sign = "+";
                return addition(num1, num2);
            case "sub":
                ServletCalculator.sign = "-";
                return subtraction(num1, num2);
            case "mult":
                ServletCalculator.sign = "*";
                return multiplication(num1, num2);
            case "div":
                ServletCalculator.sign = "/";
                return division(num1, num2);
            default:
                ServletCalculator.bugReport = "Incorrect operation";
                return 0;
        }
    }
    
}
