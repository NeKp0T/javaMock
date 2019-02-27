package com.example.mock;

import org.junit.jupiter.api.*;
import org.mockito.InOrder;
import org.mockito.Mock;

import java.util.LinkedList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CalculatorTest {
    private Calculator calc;

//    @Mock
    private Stack<Integer> mockStack;

    @BeforeEach
    void initCalc() {
        calc = new Calculator(mockStack);
    }

    @BeforeEach
    void initMock() {
        mockStack = mock(Stack.class);
    }

    void testBinaryOperation(Integer firstArg, Integer secondArg, Integer result, String sign) {
//        mockStack.push(eq(firstArg));
//        mockStack.push(eq(secondArg));
//        mockStack.push(eq(result));

        when(mockStack.pop())
//                .thenReturn(secondArg)
//                .thenReturn(firstArg)
                .thenReturn(result);
        when(mockStack.push(any()))
                .thenReturn(firstArg)
                .thenReturn(secondArg)
                .thenReturn(result);

        when(mockStack.size())
                .thenReturn(2)
                .thenReturn(1);

        calc.calculate("" + firstArg + " " + secondArg + " " + sign);

//        InOrder inOrder = inOrder(mockStack);

        verify(mockStack).push(eq(firstArg));
        verify(mockStack).push(eq(secondArg));
        verify(mockStack).push(eq(result));
    }

    @Test
    void testAdd() {
        testBinaryOperation(1,2,3,"+");
    }

    @Test
    void testSubtract() {
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

    @Test
    void addWithoutMock() {
        calc = new Calculator(new Stack<>());
        assertEquals(3, calc.calculate("1 2 +"));
    }

//    @Test
//    void mockitoWorks() {
//        //You can mock concrete classes, not just interfaces
//        LinkedList mockedList = mock(LinkedList.class);
//
//        //stubbing
//        when(mockedList.get(0)).thenReturn("first");
//        when(mockedList.get(1)).thenThrow(new RuntimeException());
//
//        //following prints "first"
//        System.out.println(mockedList.get(0));
//
//        //following throws runtime exception
//        System.out.println(mockedList.get(1));
//
//        //following prints "null" because get(999) was not stubbed
//        System.out.println(mockedList.get(999));
//
//        //Although it is possible to verify a stubbed invocation, usually it's just redundant
//        //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
//        //If your code doesn't care what get(0) returns, then it should not be stubbed. Not convinced? See here.
//        verify(mockedList).get(0);
//    }
}
