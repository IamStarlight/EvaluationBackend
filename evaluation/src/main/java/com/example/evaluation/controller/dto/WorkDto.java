package com.example.evaluation.controller.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class WorkDto {

    @NotNull(message = "作业id不能为空")
    private Integer wid;

    @NotBlank(message = "课程id不能为空")
    private Integer cid;

    @NotBlank(message = "标题不能为空")
    private String title;

    private String details;

    @NotNull(message = "截止时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Future
    private Date endTime;

    //可以为空
    private String url;
}
