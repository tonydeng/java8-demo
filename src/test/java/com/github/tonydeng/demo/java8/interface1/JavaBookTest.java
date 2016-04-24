package com.github.tonydeng.demo.java8.interface1;

import com.github.tonydeng.demo.java8.BaseTest;
import org.junit.Test;

/**
 * Created by tonydeng on 16/4/24.
 */
public class JavaBookTest extends BaseTest {
    @Test
    public void test() {
        BookInterface book = new JavaBookImpl();

        book.openTheBook();
        book.readTheBook();
        book.closeTheBook();

        JavaBookImpl javaBook = new JavaBookImpl();

        javaBook.closeTheBook();
    }
}
