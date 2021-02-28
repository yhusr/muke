package com.course.model;

import lombok.Data;

@Data
public class GetUserInfo {
    private int id;
    private int userId;
    private String expected;
}
