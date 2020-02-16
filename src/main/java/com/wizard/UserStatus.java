package com.wizard;
/*
* 用户状态枚举
* */
public enum UserStatus {
    VALID(1,"有效"),
    INVALID(0,"无效");
    Integer value;
    String name;

    public String getName(){return name;}

    public Integer getValue(){return value;}

    UserStatus(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
