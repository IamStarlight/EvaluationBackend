package com.example.evaluation.controller.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class TeacherEvaDto {
    @NotBlank(message = "学生id不能为空")
    private String sid;

    @NotBlank(message = "作业id不能为空")
    private String wid;

    @NotBlank(message = "课程id不能为空")
    private String cid;

    @NotBlank(message = "教师评分不能为空")
    private Integer teacherGrade;

    @NotBlank(message = "教师评论不能为空")
    private String teacherComments;
}
