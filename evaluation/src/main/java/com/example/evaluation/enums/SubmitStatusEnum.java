package com.example.evaluation.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum SubmitStatusEnum implements IEnum<Integer> {
    NOT_SUBMIT(1,"未交"),
    SUBMITTED(2,"已交"),
    LATE(3,"迟交");

    private Integer per;
    private String desc;

    SubmitStatusEnum(int per, String desc){
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
