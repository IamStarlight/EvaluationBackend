package com.example.evaluation.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum StatusEnum implements IEnum<Integer> {
    EDITING(1,"editing"),
    RELEASED(2,"released"),
    OVER(3,"over"),
    TEACHER_EVALUATING(4,"teacher evaluating"),
    MUTUAL_EVALUATING(5,"mutual evaluating"),
    GRADE_RELEASED(6,"grade released");

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
