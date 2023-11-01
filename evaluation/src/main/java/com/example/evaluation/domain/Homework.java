package com.example.evaluation.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("homework")
public class Homework implements Serializable {

    @TableId(value = "wid",type=IdType.AUTO)
    private String Wid;

//    @TableId(value = "cid")
    @TableField(value = "cid")
    @NotBlank(message = "课程id不能为空")
    private String Cid;

    @TableField(value = "title")
    @NotBlank(message = "用户名不能为空")
    private String Title;

    @TableField(value = "start_time")
    @NotBlank(message = "开始时间不能为空")
    private Date StartTime;

    @TableField(value = "end_time")
    @NotBlank(message = "截止时间不能为空")
    private Date EndTime;

    @TableField(value = "edit_status")
    @NotBlank(message = "编辑状态不能为空")
    private String EditStatus;

    @TableField(value = "eva_status")
    @NotBlank(message = "互评状态不能为空")
    private String EvaStatus;

    @TableField(value = "url")
    @NotBlank(message = "URL不能为空")
    private String URL;
}
