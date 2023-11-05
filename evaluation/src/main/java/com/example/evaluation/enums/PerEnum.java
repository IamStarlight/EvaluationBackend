package com.example.evaluation.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum PerEnum implements IEnum<String>{
    ROLE_ADMIN("1","ROLE_ADMIN"),
    ROLE_TEACHER("2","ROLE_TEACHER"),
    ROLE_STUDENT("3","ROLE_STUDENT");

    private String per;
    private String desc;

    PerEnum(String per, String desc){
        this.per = per;
        this.desc = desc;
    }

    @Override
    public String toString(){
        return desc;
    }

    @Override
    public String getValue() {
        return per;
    }
}
