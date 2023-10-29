package com.lantu.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cid;

    private String cname;


    private Integer tid;

    private String courseContent;

    public Course(Integer cid, String cname,Integer tid, String courseContent) {
        this.cid = cid;
        this.cname = cname;
        this.tid = tid;
        this.courseContent = courseContent;
    }

    public Course() {

    }

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

    public Integer getCid() {
        return cid;
    }

    public String getCname() {
        return cname;
    }

    public Integer getTid() {
        return tid;
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

    @Override
    public String toString() {
        return "Course{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", tid='" + tid + '\'' +
                ", courseContent='" + courseContent + '\'' +
                '}';
    }
}
