package com.duoqu.demo.java8;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tonydeng on 15/5/14.
 */
public class BaseTest {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    @Test
    public void testJdk8(){
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World!");

        list.forEach(x -> System.out.print(x));
    }
}
