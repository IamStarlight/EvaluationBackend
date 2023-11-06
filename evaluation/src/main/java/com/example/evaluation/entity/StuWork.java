package com.example.evaluation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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

    @TableId(type= IdType.AUTO)
    private Integer id;

    @TableField(value = "sid")
    private Integer sid;

    @TableField(value = "wid")
    private Integer wid;

    @TableField(value = "url")
    @NotBlank(message = "url不能为空")
    private String url;

    @TableField(value = "submit_time")
    @NotBlank(message = "提交时间不能为空")
    private Date submitTime;

    @TableField(value = "is_late")
    @NotBlank(message = "是否晚交不能为空")
    private Integer isLate;

    @TableField(value = "total_grade")
    @NotBlank(message = "总成绩不能为空")
    private Integer totalGrade;

    @TableField(value = "teacher_grade")
    @NotBlank(message = "教师评分不能为空")
    private Integer teacherGrade;

    @TableField(value = "teacher_comments")
    @NotBlank(message = "教师评论不能为空")
    private String teacherComments;

}
