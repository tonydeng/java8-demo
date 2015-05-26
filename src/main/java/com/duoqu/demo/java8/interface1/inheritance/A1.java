package com.duoqu.demo.java8.interface1.inheritance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tonydeng on 15/5/25.
 */
public interface A1 {
    public static Logger log = LoggerFactory.getLogger(A1.class);
    default void say(int a){
        log.info("say a1:'{}'",a);
    }

    default void run(){
        log.info("A1 run");
    }
}
