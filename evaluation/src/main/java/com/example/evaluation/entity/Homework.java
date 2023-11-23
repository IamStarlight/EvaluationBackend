package com.example.evaluation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.evaluation.enums.StatusEnum;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("homework")
public class Homework implements Serializable {

    @MppMultiId
    @TableField
    @NotNull(message = "作业id不能为空")
    private Integer wid;

    @MppMultiId
    @TableField
    @NotNull(message = "课程id不能为空")
    private Integer cid;

    @TableField
    private String title;

    @TableField
    private String details;

    @TableField
    private String url;

    @TableField
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @FutureOrPresent
    private Date startTime;

    @TableField
//    @NotNull(message = "截止时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Future
    private Date endTime;

    @TableField
    private Date evaDdl;

    @TableField
    private Integer submitNumber;

    @TableField
    private Integer evaNumber;

    @TableField
    private String status;

    @TableField
    private String evaStatus;

}
