package com.course.reporter;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ReporterOnTestNg {
    @Test
    public void test1(){
        Assert.assertEquals(1,1);
    }
    @Test
    public void test2(){
        Assert.assertEquals("aaa","aaa");
    }
    @Test
    public void test3(){
        Assert.assertEquals(1,2);
    }
    @Test
    public void testLog(){
        Reporter.log("这是自己创建的日志");
        throw new RuntimeException("这是自己抛出的异常");
    }

}
