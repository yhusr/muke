package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupOnTeacher1 {
    public void groupTeacher(){
        System.out.println("teacher1111111111");
    }
}
