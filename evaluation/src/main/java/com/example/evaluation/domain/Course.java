package com.example.evaluation.domain;

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

    @TableId(value = "cid",type=IdType.AUTO)
    private String Cid;

    @TableField(value = "cname")
    @NotBlank(message = "课程名不能为空")
    private String Cname;

    @TableField(value = "tid")
    @NotBlank(message = "授课教师不能为空")
    private String Tid;

    @TableField(value = "content")
    @NotBlank(message = "课程名不能为空")
    private String Content;


}
