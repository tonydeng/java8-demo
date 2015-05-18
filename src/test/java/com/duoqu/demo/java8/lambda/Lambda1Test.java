package com.duoqu.demo.java8.lambda;

import com.duoqu.demo.java8.BaseTest;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tonydeng on 15/5/18.
 */
public class Lambda1Test extends BaseTest {
    private static List<String>  names =Arrays.asList("peter","anna","mike","xenia");

    @Test
    public void oldVersionCompareTest(){
        List<String> list = names;
        Lambda1.oldVersionCompare(list);
        log.info("oldVersionCopare list：‘{}’",list);
    }

    @Test
    public void lambdaCompareTest(){
        List<String> list = names;
        Lambda1.lambdaCompare(list);
        log.info("lambdaCompare list：‘{}’",list);
    }
}
