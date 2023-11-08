package com.example.evaluation.controller.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class SubmitDto {
    @NotBlank(message = "学生id不能为空")
    private Integer sid;

    @NotBlank(message = "作业id不能为空")
    private Integer wid;

    @NotBlank(message = "课程id不能为空")
    private Integer cid;

    private String details;

    private String url;

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
//    @NotBlank(message = "提交时间不能为空")
//    private Date submitTime;
}
