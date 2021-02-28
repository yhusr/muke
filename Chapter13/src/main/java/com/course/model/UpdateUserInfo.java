package com.course.model;

import lombok.Data;

@Data
public class UpdateUserInfo {
    private int id;
    private int userId;
    private String userName;
    private int sex;
    private int age;
    private int permission;
    private int isDelete;
}
