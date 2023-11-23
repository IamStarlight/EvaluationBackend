package com.example.evaluation.controller.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class AppealDto {
    @NotNull(message = "学号不能为空")
    Integer sid;

    @NotNull(message = "作业号不能为空")
    Integer wid;

    @NotNull(message = "课程号不能为空")
    Integer cid;

//    @NotNull(message = "申诉理由不能为空")
    String reason;

    String reply;
}
