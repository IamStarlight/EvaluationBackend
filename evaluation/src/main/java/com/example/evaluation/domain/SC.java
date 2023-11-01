package com.example.evaluation.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SC implements Serializable {

    @TableId(value = "cid")
    @NotBlank(message = "课程id不能为空")
    private String CId;

    @TableField(value = "sid")
    @NotBlank(message = "学生id不能为空")
    private String Sid;

    @TableField(value = "grade")
    @NotBlank(message = "成绩不能为空")
    private Integer Grade;

}