package com.duoqu.demo.java8.lambda;

import com.duoqu.demo.java8.BaseTest;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tonydeng on 15/5/18.
 */
public class Lambda1Test extends BaseTest {
    private static List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

    //    @Test
    public void oldVersionCompareTest() {
        List<String> list = names;
        Lambda1.oldVersionCompare(list);
        log.info("oldVersionCopare list：‘{}’", list);
    }

    //    @Test
    public void lambdaCompareTest() {
        List<String> list = names;
        Lambda1.lambdaCompare(list);
        log.info("lambdaCompare list：‘{}’", list);
    }

    @Test
    public void functionalInterfaceTest() {
        Lambda1.Converter<String, Integer> converter = (form) -> Integer.valueOf(form);
        Integer converted = converter.convert("123");

        log.info("functionalInterfaceTest converted:'{}'", converted);
    }

    @Test
    public void staticFunctionTest() {
        Lambda1.Converter<String, Integer> converter = Integer::valueOf;

        Integer converted = converter.convert("456");

        log.info("staticFunctionTest converted:'{}'", converted);
    }

    @Test
    public void objectFunctionTest() {
        class Something {
            String startsWith(String s) {
                return String.valueOf(s.charAt(0));
            }
        }
        Something something = new Something();
        Lambda1.Converter<String, String> converter = something::startsWith;

        String converted = converter.convert("Java");

        log.info("objectFunctionTest converted:'{}'", converted);
    }

    @Test
    public void finalValTest() {
        final int num = 1;
        Lambda1.Converter<Integer, String> stringConverter = (form) -> String.valueOf(form + num);

        String converted = stringConverter.convert(2);

        log.info("finalValTest converted:'{}'", converted);
    }


    static int outerStaticNum;
    int outerNum;

    @Test
    public void testScope() {
        Lambda1.Converter<Integer, String> stringConverter1 = (form) -> {
            outerNum = 23;
            return String.valueOf(outerNum * outerStaticNum * form);
        };

        Lambda1.Converter<Integer, String> stringConverter2 = (form) -> {
            outerStaticNum = 72;
            return String.valueOf(outerNum * outerStaticNum * form);
        };

        String converted1 = stringConverter1.convert(2);

        log.info("testScope stringConverter1 converted:'{}'", converted1);

        String converted2 = stringConverter2.convert(2);

        log.info("testScope stringConverter2 converted:'{}'", converted2);
    }

}
