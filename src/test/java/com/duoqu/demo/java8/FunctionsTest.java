package com.duoqu.demo.java8;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

/**
 * Created by tonydeng on 15/5/26.
 */
public class FunctionsTest extends BaseTest {
    @Test
    public void test() {
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        Assert.assertEquals("123", backToString.apply("123"));
    }
}
