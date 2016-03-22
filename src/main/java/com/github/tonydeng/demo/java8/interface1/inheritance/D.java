package com.github.tonydeng.demo.java8.interface1.inheritance;

/**
 * Created by tonydeng on 15/5/25.
 */
public class D implements C {
    public void sayHi(){
        byte a = 1;
        say(a);     // 会调用B的say方法，请参考Java规范15.12.2.5
    }
}
