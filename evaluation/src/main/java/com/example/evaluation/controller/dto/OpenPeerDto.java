package com.example.evaluation.controller.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OpenPeerDto {
    @NotNull(message = "作业号不能为空")
    Integer wid;

    @NotNull(message = "课程号不能为空")
    Integer cid;

    @NotNull(message = "课程状态不能为空")
    Integer status;

    @NotNull(message = "互评截止时间不能为空")
    Date ddl;
}
