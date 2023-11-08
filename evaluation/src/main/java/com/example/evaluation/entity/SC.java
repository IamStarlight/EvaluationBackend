package com.example.evaluation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
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

    @MppMultiId
    @TableField
    @NotBlank(message = "课程id不能为空")
    private Integer cid;

    @MppMultiId
    @TableField
    @NotBlank(message = "学生id不能为空")
    private Integer sid;

    @TableField
    @NotBlank(message = "成绩不能为空")
    private Integer grade;

}