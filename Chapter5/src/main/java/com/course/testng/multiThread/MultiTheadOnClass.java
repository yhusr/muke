package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiTheadOnClass {
    @Test(invocationCount = 10,threadPoolSize = 3)
    public void testPool(){
        /**
         *
         * */
        System.out.printf("threadNum %s%n",Thread.currentThread().getId());
    }
}
