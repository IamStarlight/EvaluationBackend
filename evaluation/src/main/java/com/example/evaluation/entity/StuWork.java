package com.example.evaluation.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("stu_homework")
public class StuWork implements Serializable {
    @TableField(value = "sid")
    private String Sid;

    @TableField(value = "wid")
    private String Wid;

    @TableField(value = "url")
    @NotBlank(message = "url不能为空")
    private String URL;

    @TableField(value = "submit_time")
    @NotBlank(message = "提交时间不能为空")
    private Date SubmitTime;

    @TableField(value = "is_late")
    @NotBlank(message = "是否晚交不能为空")
    private Integer isLate;

    @TableField(value = "total_grade")
    @NotBlank(message = "总成绩不能为空")
    private Integer TotalGrade;

    @TableField(value = "teacher_grade")
    @NotBlank(message = "教师评分不能为空")
    private Integer TeacherGrade;

    @TableField(value = "teacher_comments")
    @NotBlank(message = "教师评论不能为空")
    private String TeacherComments;

}
