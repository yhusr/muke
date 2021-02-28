package com.course.model;

import lombok.Data;

@Data
public class AddUser {
    private int id;
    private String userName;
    private String password;
    private int sex;
    private int age;
    private int permission;
    private int isDelete;
    private String expected;
}
