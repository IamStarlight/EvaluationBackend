package com.lantu.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class SC implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer cid;

    private Integer sid;


    private Integer grade;

    public SC(Integer cid, Integer sid, Integer grade) {
        this.cid = cid;
        this.sid = sid;
        this.grade = grade;
    }

    public Integer getCid() {
        return cid;
    }

    public Integer getSid() {
        return sid;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "SC{" +
                "cid=" + cid +
                ", sid=" + sid +
                ", grade=" + grade +
                '}';
    }
}
