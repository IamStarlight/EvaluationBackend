package com.example.evaluation.controller.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class EvaDto {

    @NotNull(message = "被评学生id不能为空")
    private Integer sid;

    @NotNull(message = "作业id不能为空")
    private Integer wid;

    @NotNull(message = "课程id不能为空")
    private Integer cid;


    @NotNull(message = "评分不能为空")
    @Max(value = 100, message = "评分必须小于等于100")
    @Min(value = 0, message = "评分必须大于等于0")
    private Integer grade;

//    @NotBlank(message = "评论不能为空")
    private String comments;
}
