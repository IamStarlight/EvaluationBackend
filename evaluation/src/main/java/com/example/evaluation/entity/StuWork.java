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
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("stu_homework")
public class StuWork implements Serializable {

    @MppMultiId
    @TableField
    private Integer sid;

    @MppMultiId
    @TableField
    private Integer wid;

    @MppMultiId
    @TableField
    private Integer cid;

    @TableField
    private String details;

    @TableField
    private String url;

    @TableField
    private Date submitTime;

    @TableField
    @NotBlank(message = "是否晚交不能为空")
    private boolean isLate;

    @TableField
    private Integer totalGrade;

    @TableField
    private Integer teacherGrade;

    @TableField
    private String teacherComments;

}
