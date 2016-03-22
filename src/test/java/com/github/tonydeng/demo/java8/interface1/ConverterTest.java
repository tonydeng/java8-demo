package com.github.tonydeng.demo.java8.interface1;

import com.github.tonydeng.demo.java8.BaseTest;
import org.junit.Test;

/**
 * Created by tonydeng on 16/3/22.
 */
public class ConverterTest extends BaseTest {

    @Test
    public void testStringToInt() {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer result = converter.convert("123");
        log.info("converted:'{}'  class'{}'", result, result.getClass());
    }
}
