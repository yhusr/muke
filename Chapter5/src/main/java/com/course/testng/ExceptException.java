package com.course.testng;

import org.testng.annotations.Test;

public class ExceptException {

    /**
     * 异常测试
     * 期望抛出的异常类型：@Test(expectedExceptions = RuntimeException.class)
     * 实际抛出的异常：throw new RuntimeException();
     * 期望与实际一致：测试通过
     * 期望与实际不一致：测试失败
     * */
    @Test(expectedExceptions = RuntimeException.class)
    public void exceptExceptionFailed(){
        System.out.println("这是抛出异常失败的测试");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void exceptExceptionSuccess(){
        System.out.println("这是抛出异常成功的测试");
        throw new RuntimeException();
    }
}
