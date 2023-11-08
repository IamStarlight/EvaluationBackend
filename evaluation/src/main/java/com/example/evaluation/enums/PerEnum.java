package com.example.evaluation.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum PerEnum{
    ROLE_ADMIN(1,"ROLE_ADMIN"),
    ROLE_TEACHER(2,"ROLE_TEACHER"),
    ROLE_STUDENT(3,"ROLE_STUDENT");

    private Integer per;
    private String desc;

    PerEnum(Integer per, String desc){
        this.per = per;
        this.desc = desc;
    }

    public String toString(){
        return desc;
    }

    public Integer getValue() {
        return per;
    }
}
