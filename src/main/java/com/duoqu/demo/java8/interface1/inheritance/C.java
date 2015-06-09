package com.duoqu.demo.java8.interface1.inheritance;

/**
 * Created by tonydeng on 15/5/25.
 */
public interface C extends A, B {
    @Override
    default String say(String name) {
        return "greet " + name;
    }
}
