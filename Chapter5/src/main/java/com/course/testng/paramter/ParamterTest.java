package com.course.testng.paramter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParamterTest {
    /**
     * 参数传递
     * 使用xml文件传递
     * */
    @Test
    @Parameters({"name","age"})
    public void paramTest(String name, int age){
        System.out.println("name = " + name + "; age=" + age);
    }
}
