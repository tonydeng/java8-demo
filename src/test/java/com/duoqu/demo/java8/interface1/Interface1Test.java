package com.duoqu.demo.java8.interface1;

import com.duoqu.demo.java8.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tonydeng on 15/5/15.
 */
public class Interface1Test extends BaseTest {

    @Test
    public void testFormula() {
        Interface1.Formula formula = new Interface1.Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        Assert.assertTrue((100 == formula.calculate(100)));
        log.info("100 calculate:'{}'", formula.calculate(100));
        Assert.assertTrue((0.0 == formula.sqrt(-23)));
        log.info("-23 sqrt:'{}'", formula.sqrt(-23));
        Assert.assertTrue((0 == Interface1.Formula.positive(-4)));
        log.info("-4 positive:'{}'", Interface1.Formula.positive(-4));
    }

}
