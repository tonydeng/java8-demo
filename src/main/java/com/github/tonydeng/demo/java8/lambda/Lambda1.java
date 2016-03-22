package com.github.tonydeng.demo.java8.lambda;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by tonydeng on 15/5/15.
 */
public class Lambda1 {
    public static List<String> oldVersionCompare(List<String> list) {
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        return list;
    }

    public static List<String> lambdaCompare(List<String> list){
        Collections.sort(list,(a,b) -> b.compareTo(a));
        return list;
    }

    @FunctionalInterface
    interface Converter<F,T>{
        T convert(F form);
    }
}
