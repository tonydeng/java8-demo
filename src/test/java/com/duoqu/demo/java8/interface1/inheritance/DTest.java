package com.duoqu.demo.java8.interface1.inheritance;

import com.duoqu.demo.java8.BaseTest;
import org.junit.Test;

/**
 * Created by tonydeng on 15/5/25.
 */
public class DTest extends BaseTest {
    @Test
    public void sayHiTest(){
        D d = new D();
        d.sayHi();
    }
}
