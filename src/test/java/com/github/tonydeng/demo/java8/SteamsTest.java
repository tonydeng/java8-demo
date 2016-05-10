package com.github.tonydeng.demo.java8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by tonydeng on 15/5/27.
 */
public class SteamsTest extends BaseTest {

    private static final List<String> stringCollection = Lists.newArrayList(
            "ddd2", "aaa2", "bbb1", "aaa1", "bbb3", "ccc", "bbb2", "ddd1"
    );

    @Test
    public void addTest(){

        Stream<String> sl = stringCollection.stream();
        stringCollection.add("add");

        sl.forEach(log::info);
    }

    @Test
    public void filterTest() {
        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach((s) -> log.info("filter test:'{}'", s));
    }

    @Test
    public void sortedTest() {
        List<String> list = Lists.newArrayList();
        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("b"))
                .forEach((s) -> log.info("sorted test:'{}'", s));

        stringCollection.stream()
                .sorted()
                .filter((s) -> s.startsWith("b"))
                .forEach(
                        (s) -> list.add(s)
                );

        log.info("sorted list:'{}'", list);
    }

    @Test
    public void mapTest() {
        stringCollection.stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> a.compareTo(b))
                .forEach(log::info);
    }

    @Test
    public void matchTest(){
        boolean anyStartsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));

        log.info("anyStartsWithA:'{}'",anyStartsWithA);


        boolean allStartsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("a"));

        log.info("allStartsWithA:'{}'",allStartsWithA);

        boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));

        log.info("noneStartsWithZ:'{}'",noneStartsWithZ);
    }
    @Test
    public void countTest(){
        long startsWithB = stringCollection
                .stream().filter((s) -> s.startsWith("b"))
                .count();
        log.info("startsWithB:'{}'",startsWithB);
    }

    @Test
    public void reduceTest(){
        Optional<String> reduce = stringCollection.stream()
                .sorted().reduce((s1,s2) -> s1+"#"+s2);

        reduce.ifPresent(log::info);
    }
}
