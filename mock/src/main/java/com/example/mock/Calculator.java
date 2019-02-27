package com.example.mock;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    private Stack <Integer> values;

    public Calculator(Stack<Integer> values) {
        this.values = values;
    }

    public int calculate(String toCalculate) {
        var scanner = new Scanner(toCalculate);
        while (scanner.hasNext()) {
            var operation = scanner.next();
            var operationSymbol = operation.charAt(0);
            if ('0' <= operationSymbol && operationSymbol <= '9') {
                values.push(Integer.parseInt(operation));
                continue;
            }
            if (values.size() < 2) {
                throw new IllegalArgumentException();
            }
            var lastValue = values.pop();
            var value = values.pop();
            switch (operationSymbol) {
                case '+':
                    values.push(value + lastValue);
                case '-':
                    values.push(value - lastValue);
                case '*':
                    values.push(value * lastValue);
                case '/':
                    values.push(value / lastValue);
            }
        }
        if (values.size() != 1) {
            throw new IllegalArgumentException();
        }
        return values.pop();
    }
}
