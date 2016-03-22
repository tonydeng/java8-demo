package com.github.tonydeng.demo.java8.interface1;

/**
 * Created by tonydeng on 16/3/22.
 */
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}
