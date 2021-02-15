package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {
    @Test(groups = "server")
    public void testServer1(){
        System.out.println("这是server1端测试");
    }
    @Test(groups = "server")
    public void testServer2(){
        System.out.println("这是server2端测试");
    }
    @Test(groups = "client")
    public void testClient1(){
        System.out.println("这是client1端测试");
    }
    @Test(groups = "client")
    public void testClient2(){
        System.out.println("这是client2端测试");
    }
    @BeforeGroups("server")
    public void groupBeforeServer(){
        System.out.println("这是服务端测试开始之前运行");
    }
    @AfterGroups("server")
    public void groupAfterServer(){
        System.out.println("这是服务端测试开始之后运行");
    }
    @BeforeGroups("client")
    public void groupBeforeClient(){
        System.out.println("这是客户端测试开始之前运行");
    }
    @AfterGroups("client")
    public void groupAfterClient(){
        System.out.println("这是客户端测试开始之后运行");
    }
}
