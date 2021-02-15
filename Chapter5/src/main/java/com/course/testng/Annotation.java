package com.course.testng;


import org.testng.annotations.*;

public class Annotation {
    @Test
    public void testCase(){
        System.out.println("这是测试用例1");
    }
    @Test
    public void testCase1(){
        System.out.println("这是测试用例2");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("这是测试开始之前运行的");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("这是测试方法结束后运行的");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("这是在类运行之前运行的");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("这是在类运行之后运行的");
    }
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("这是beforesuite测试套件");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("这是aftersuite测试套件");
    }
    @BeforeGroups
    public void beforeGroups(){
        System.out.println("这是beforeGroups测试组");
    }
    @AfterGroups
    public void afterGroups(){
        System.out.println("这是afterGroups测试组");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("这是beforetest的测试");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("这是aftertest的测试");
    }
}
