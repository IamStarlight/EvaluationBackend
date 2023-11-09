package com.example.evaluation.controller.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TeacherEvaDto {
    @NotNull(message = "学生id不能为空")
    private Integer sid;

    @NotNull(message = "作业id不能为空")
    private Integer wid;

    @NotNull(message = "课程id不能为空")
    private Integer cid;

    @NotNull(message = "教师评分不能为空")
    private Integer teacherGrade;

//    @NotBlank(message = "教师评论不能为空")
    private String teacherComments;
}
