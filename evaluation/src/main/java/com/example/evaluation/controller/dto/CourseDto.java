package com.example.evaluation.controller.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CourseDto {

    @NotBlank(message = "课程名不能为空")
    private String cname;

    @NotBlank(message = "授课教师不能为空")
    private String tid;

    @NotBlank(message = "课程名不能为空")
    private String content;
}
