package com.github.tonydeng.demo.java8.interface1;

/**
 * Created by tonydeng on 16/4/24.
 */
public class JavaBookImpl implements BookInterface {
    @Override
    public void openTheBook() {
        System.out.println("The Java book is opened!");
    }

    @Override
    public void readTheBook() {
        System.out.println("Reading the Java book!");
    }

    @Override
    public void closeTheBook() {
        System.out.println("Close the Java book!");
    }
}
