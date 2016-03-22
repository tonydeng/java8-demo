package com.github.tonydeng.demo.java8.interface1.inheritance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tonydeng on 15/5/25.
 */
public interface B {
    public static final Logger log = LoggerFactory.getLogger(B.class);
    default String say(String name) {
        return "hi " + name;
    }

    default void say(byte[] name){
        log.info("say b:'{}'",name);
    }

    default void say(short a){
        log.info("say b:'{}'",a);
    }
}
