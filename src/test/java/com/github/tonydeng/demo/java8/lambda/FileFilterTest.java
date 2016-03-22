package com.github.tonydeng.demo.java8.lambda;

import com.github.tonydeng.demo.java8.BaseTest;
import org.junit.Test;

import java.io.File;

/**
 * Created by tonydeng on 16/3/22.
 */
public class FileFilterTest extends BaseTest {

    @Test
    public void testGetDirs(){
        File[] dirs = FileFilter.getDirs(new File("/usr"));

        for(File d:dirs){
            log.info("dir:'{}'",d.getPath());
        }
    }
}
