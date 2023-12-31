package com.example.evaluation.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum StatusEnum implements IEnum<Integer> {
    DRAFT(1,"草稿"),
    RELEASED(2,"已发布"),
    OVER(3,"已截止");

    private Integer per;
    private String desc;

    StatusEnum(int per, String desc){
        this.per = per;
        this.desc = desc;
    }

    @Override
    public String toString(){
        return desc;
    }

    @Override
    public Integer getValue() {
        return per;
    }
}
