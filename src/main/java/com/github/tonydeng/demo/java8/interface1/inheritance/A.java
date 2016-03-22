package com.github.tonydeng.demo.java8.interface1.inheritance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tonydeng on 15/5/25.
 */
public interface A {
    public static final Logger log = LoggerFactory.getLogger(A.class);
    default String say(String name){
        return "hello "+name;
    }

    default void say(CharSequence name){
        log.info("say a:'{}'",name);
    }

    default void say(int a){
        log.info("say a:'{}'",a);
    }

}
