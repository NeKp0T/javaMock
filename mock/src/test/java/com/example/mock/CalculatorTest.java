package com.example.mock;

import org.junit.jupiter.api.*;
import org.mockito.InOrder;
import org.mockito.Mock;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CalculatorTest {
    private Calculator calc;

    @Mock
    private Stack<Integer> mockStack;

    @BeforeEach
    void initCalc() {
        calc = new Calculator(mockStack);
    }

    void testBinaryOperation(int firstArg, int secondArg, int result, String sign) {
        InOrder inOrder = inOrder(mockStack);

        inOrder.verify(mockStack).push(eq(firstArg));
        inOrder.verify(mockStack).push(eq(secondArg));
        inOrder.verify(mockStack).push(eq(result));

        when(mockStack.pop())
                .thenReturn(secondArg)
                .thenReturn(firstArg)
                .thenReturn(result);

        assertEquals(3, Calculator.calculate("" + firstArg + " " + secondArg + " " + sign));
    }

    @Test
    void testAdd() {
        testBinaryOperation(1,2,3,"+");
    }

    @Test
    void testSubstract() {
        testBinaryOperation(1,2,-1,"-");
    }

    @Test
    void testMultiply() {
        testBinaryOperation(2,3,6,"*");
    }

    @Test
    void testDivide() {
        testBinaryOperation(10,4,2,"/");
    }

}
