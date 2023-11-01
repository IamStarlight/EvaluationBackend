package com.example.evaluation.controller.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class WorkDto {
    @NotBlank(message = "作业id不能为空")
    private String Wid;

    @NotBlank(message = "用户名不能为空")
    private String Title;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotBlank(message = "开始时间不能为空")
    private Date StartTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotBlank(message = "结束时间不能为空")
    private Date EndTime;

    @NotBlank(message = "编辑状态不能为空")
    private String EditStatus;

    @NotBlank(message = "互评状态不能为空")
    private String EvaluateStatus;

    //可以为空
    private String URL;
}
