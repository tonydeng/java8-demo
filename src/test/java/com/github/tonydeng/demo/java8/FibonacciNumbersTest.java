package com.github.tonydeng.demo.java8;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by tonydeng on 16/5/12.
 */
@RunWith(Parameterized.class)
public class FibonacciNumbersTest {
    private static final Logger log = LoggerFactory.getLogger(FibonacciNumbersTest.class);

    @Parameterized.Parameters
    public static Collection<Integer[]> data() {
        return Arrays.asList(new Integer[][]{
                {0, 0}, {1, 1}, {2, 1},
                {3, 2}, {4, 3}, {5, 5},
                {6, 8}});
    }

    private int value;
    private int expected;

    public FibonacciNumbersTest(int input, int expected) {
        value = input;
        this.expected = expected;
    }

    @Test
    public void fibonacciNumberCall() {
        log.info("expected {} fib {}", expected, fib(value));
        Assert.assertEquals(expected, fib(value));
    }

    public static int fib(int n) {
        if (n < 2) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
}
