package com.course.testng.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class ProviderDate {
    @Test(dataProvider = "data")
    public void provider1(String name, int age){
        System.out.println("我的姓名:" + name + ";我的年龄：" + age);
    }

    @Test(dataProvider = "data")
    public void provider2(String name, int age){
        System.out.println("pro2的name=" + name + ";pro2的age=" + age);
    }

//    @DataProvider(name = "data")
//    public Object[][] getDate(){
//        /**
//         * 数据传递dataprovider属性
//         * */
//        Object[][] a = {
//                {"zhangsan",23},
//                {"zhaosi",34}
//        };
//        return a;
//    }

    @DataProvider(name = "data")
    public Object[][] findMethod(Method method){
        Object[][] result = null;
        if(method.getName().equals("provider1")){
            result = new Object[][]{
                    {"pro1",11},
                    {"pro11",21},
                    {"pro21",31}
            };
        }else if(method.getName().equals("provider2")){
            result = new Object[][]{
                    {"pro2",22},
                    {"pro22",32},
                    {"pro32",42},
            };
        }

        return result;
    }

}
