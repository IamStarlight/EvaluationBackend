package com.example.evaluation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("course")
public class Course implements Serializable {

    @TableId(type=IdType.AUTO)
    @NotNull(message = "课程号不能为空")
    private Integer cid;

    @TableField
    @NotBlank(message = "课程名不能为空")
    private String cname;

    @TableField
    @NotNull(message = "授课教师工号不能为空")
    private Integer tid;

    @TableField
//    @NotBlank(message = "课程简介不能为空")
    private String content;

    @TableField
//    @NotNull(message = "选课人数不能为空")
    private Integer classNumber;

}
