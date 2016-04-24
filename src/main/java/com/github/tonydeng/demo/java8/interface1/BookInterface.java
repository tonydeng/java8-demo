package com.github.tonydeng.demo.java8.interface1;

/**
 * Created by tonydeng on 16/4/24.
 */
public interface BookInterface {
    public void openTheBook();

    public void readTheBook();

    public default void closeTheBook(){
        System.out.println("Closting the book!");
    }
}
