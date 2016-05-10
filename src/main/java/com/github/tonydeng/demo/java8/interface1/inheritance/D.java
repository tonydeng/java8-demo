package com.github.tonydeng.demo.java8.interface1.inheritance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tonydeng on 15/5/25.
 */
public class D implements C {
    private static final Logger log = LoggerFactory.getLogger(D.class);

    public void sayHi() {
        byte b = 1;
        say(b);     // 会调用B的say方法，请参考Java规范15.12.2.5

        int i = 1;
        say(i);

        log.info(say("s"));
    }
}
