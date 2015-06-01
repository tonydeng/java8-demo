package com.duoqu.demo.java8;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.List;

/**
 * Created by tonydeng on 15/5/27.
 */
public class SteamsTest extends BaseTest {

    private static final List<String> stringCollection = Lists.newArrayList(
        "ddd2","aaa2","bbb1","aaa1","bbb3","ccc","bbb2","ddd1"
    );

    @Test
    public void filterTest(){
        stringCollection
                .stream()
                .filter((s)-> s.startsWith("a"))
                .forEach((s) -> log.info("filter test:'{}'",s));
    }

    @Test
    public void sortedTest(){
        List<String> list = Lists.newArrayList();
        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("b"))
                .forEach((s) -> log.info("sorted test:'{}'",s));

        stringCollection.stream()
                .sorted()
                .filter((s) -> s.startsWith("b"))
                .forEach(
                        (s) -> list.add(s)
                );

        log.info("sorted list:'{}'",list);
    }
}
