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

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("course")
public class Course implements Serializable {

    @TableId(type=IdType.AUTO)
    private Integer cid;

    @TableField
    @NotBlank(message = "课程名不能为空")
    private String cname;

    @TableField
    @NotBlank(message = "授课教师不能为空")
    private String tid;

    @TableField
    @NotBlank(message = "课程名不能为空")
    private String content;

    @TableField
    @NotBlank(message = "选课人数不能为空")
    private Integer number;


}
