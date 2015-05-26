package com.duoqu.demo.java8.lambda;

import com.duoqu.demo.java8.BaseTest;
import org.junit.Test;

/**
 * Created by tonydeng on 15/5/26.
 */
public class PersonFactoryTest extends BaseTest {
    @Test
    public void createTest() {
        PersonFactory<Person> personPersonFactory = Person::new;

        Person person = personPersonFactory.create("Tony", "Deng");

        log.info("person:'{}'",person);
    }
}
