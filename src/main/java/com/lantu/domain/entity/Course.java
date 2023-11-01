package com.lantu.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cid;

    private String cname;


    private Integer tid;

    private String course_content;

    public Integer getCid() {
        return cid;
    }

    public String getCname() {
        return cname;
    }

    public Integer getTid() {
        return tid;
    }

    public String getCourse_content() {
        return course_content;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public void setCourse_content(String course_content) {
        this.course_content = course_content;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", tid=" + tid +
                ", course_content='" + course_content + '\'' +
                '}';
    }
}
