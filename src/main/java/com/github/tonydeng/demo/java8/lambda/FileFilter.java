package com.github.tonydeng.demo.java8.lambda;

import java.io.File;

/**
 * Created by tonydeng on 16/3/22.
 */
public class FileFilter {
    /**
     * 获取指定文件下的目录
     *
     * @param path
     * @return
     */
    public static File[] getDirs(File path) {
        if (null != path && path.exists()) {
            return path.listFiles((File f) -> f.isDirectory());
        }
        return new File[0];
    }
}
