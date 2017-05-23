package com.wf2311.jfeng.file;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author wangfeng
 * @time 2017/05/23 19:15.
 */
public class FileTest {
    @Test
    public void test1() throws IOException {
        File file = new File("D:\\Share\\chm\\3DMAX教程.chm");
        String s = MD5FileUtil.md5(file);
        HashCode hash = Files.hash(file, Hashing.md5());
        System.out.println(s);
        System.out.println(hash);
    }
}
