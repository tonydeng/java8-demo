package com.duoqu.demo.java8;

/**
 * Created by tonydeng on 15/5/15.
 *
 * java8允许开发者通过使用关键字 default 向接口中加入非抽象方法。这一特性被称为扩展方法
 */
public class Interface1 {
    interface Formula{
        double calculate(int a);

        default double sqrt(int a){
            return Math.sqrt(positive(a));
        }
        static int positive(int a){
            return a > 0 ? a: 0;
        }
    }

}
