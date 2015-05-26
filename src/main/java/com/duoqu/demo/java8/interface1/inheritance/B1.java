package com.duoqu.demo.java8.interface1.inheritance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tonydeng on 15/5/25.
 */
public interface B1 extends A1 {
    public static Logger log = LoggerFactory.getLogger(B1.class);
    default void say(int a){
        log.info("say b1:'{}'",a);
    }

    default void play(){
        log.info("B1 run");
    }
}
