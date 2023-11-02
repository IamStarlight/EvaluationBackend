package com.example.evaluation.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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

    @TableId(value = "wid",type=IdType.AUTO)
    private String wid;

    @TableField
    @NotBlank(message = "课程id不能为空")
    private String cid;

    @TableField
    @NotBlank(message = "用户名不能为空")
    private String title;

    @TableField
    @FutureOrPresent
    private Date startTime;

    @TableField
    @NotNull(message = "截止时间不能为空")
    @Future
    private Date endTime;

    @TableField
    private String editStatus;

    @TableField
    private String evaStatus;

    @TableField
    private String url;
}
