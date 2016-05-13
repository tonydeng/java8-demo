package com.github.tonydeng.demo.java8;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestName;
import org.junit.rules.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by tonydeng on 16/5/10.
 */
public class JunitRuleTest {
    private static final Logger log = LoggerFactory.getLogger(JunitRuleTest.class);

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Rule
    public TestName name = new TestName();

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Rule
    public Timeout timeout = Timeout.seconds(5);

    @Test
    public void testTimeout() {
        while (true) ;
    }

    //    @Test
    public void testFileCreateAndWrite() throws IOException {
        File file = tempFolder.newFile("simple.txt");
        log.info("temp file:'{}'", file.getPath());
        FileUtils.writeStringToFile(file, "Junit Rules!");
        String line = FileUtils.readFileToString(file);
        Assert.assertThat(line, is("Junit Rules!"));
    }

    //    @Test
    public void testMethodName() {
        log.info("Test method name:'{}'", name.getMethodName());
    }

    //    @Test
    public void testStatementCollector() {
        String s = null;
        collector.checkThat("Value should not be null", null, is(s));

        s = "";

        collector.checkThat("Value should have the length of 1", s.length(), is(1));

        s = "Junit!";

        collector.checkThat("Value should have the length of 10", s.length(), is(10));

    }

    //    @Test
    public void testErrorCollector() {

        collector.addError(new Throwable("first thing went wrong"));
        collector.addError(new Throwable("second thing went wrong"));
    }
}
