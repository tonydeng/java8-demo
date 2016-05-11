package com.github.tonydeng.demo.java8;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by tonydeng on 16/5/10.
 */
public class JunitRuleTest {
    private static final Logger log = LoggerFactory.getLogger(JunitRuleTest.class);

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Rule
    public TestName name = new TestName();
    @Test
    public void testFileCreateAndWrite() throws IOException {
        File file = tempFolder.newFile("simple.txt");
        log.info("temp file:'{}'", file.getPath());
        FileUtils.writeStringToFile(file,"Junit Rules!");
        String line = FileUtils.readFileToString(file);
        Assert.assertEquals(line, "Junit Rules!");
    }

    @Test
    public void testMethodName(){
        log.info("Test method name:'{}'",name.getMethodName());
    }
}
