package com.example.evaluation.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sc")
public class SC implements Serializable {

    @TableId(value = "cid")
    @NotBlank(message = "课程id不能为空")
    private Integer Cid;

    @TableField(value = "sid")
    @NotBlank(message = "学生id不能为空")
    private Integer Sid;

    @TableField(value = "grade")
    @NotBlank(message = "成绩不能为空")
    private Integer Grade;

}