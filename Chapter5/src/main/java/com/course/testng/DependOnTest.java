package com.course.testng;

import org.testng.annotations.Test;

public class DependOnTest {
    /**
     * 依赖测试
     * 使用属性：@Test(dependsOnMethods = {"test1"})
     * */
    @Test
    public void test1(){
        System.out.println("这是test1运行");
    }
    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        System.out.println("这是test2运行");
    }
}
