package com.duoqu.demo.java8;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created by tonydeng on 15/5/26.
 */
public class PredicateTest extends BaseTest {
    @Test
    public void lambdaTest(){
        Predicate<String> predicate = (s) -> s.length() > 0;
        Assert.assertTrue(predicate.test("foo"));
        log.info("lambdaTest predicate.test(\"foo\"):'{}'", predicate.test("foo"));
        Assert.assertFalse(predicate.negate().test("foo"));
        log.info("lambdaTest predicate.negate().test(\"foo\"):'{}'",predicate.negate().test("foo"));
    }

    @Test
    public void staticMethodTest(){
        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = StringUtils::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();


        Assert.assertFalse(nonNull.test(null));
        log.info("staticMethodTest nonNull.test(null):'{}'",nonNull.test(null));
        Assert.assertTrue(nonNull.test(Boolean.FALSE));
        log.info("staticMethodTest nonNull.test(Boolean.FALSE):'{}'",nonNull.test(Boolean.FALSE));
        Assert.assertTrue(nonNull.test((1>2)));
        log.info("staticMethodTest nonNull.test((1>2)):'{}'",nonNull.test((1>2)));

        List<String> strings = Lists.newArrayList(
                "test",""," ",null
        );

        for(String s:strings){
            log.info("staticMethodTest isEmpty.test(\"{}\"):'{}'",s, isEmpty.test(s));
            log.info("staticMethodTest isNotEmpty.test(\"{}\"):'{}'",s, isNotEmpty.test(s));
        }

    }
}
