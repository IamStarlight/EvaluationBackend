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
//    @NotBlank(message = "作业id不能为空")
//    private String Wid;

    @NotBlank(message = "标题不能为空")
    private String title;

    private Date startTime;

    @NotNull(message = "截止时间不能为空")
    @Future
    private Date endTime;

    private String editStatus;

    private String evaStatus;

    //可以为空
    private String url;
}
