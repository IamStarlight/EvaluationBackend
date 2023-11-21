package com.example.evaluation.controller.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class NewHomeworkDto {

    private Integer wid;

    private Integer cid;

    private String title;

    private String details;

    private String url;

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @FutureOrPresent
    private Date startTime;

    @NotNull(message = "截止时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Future
    private Date endTime;

    private Integer submitNumber;

    private Integer evaNumber;

    private String status;

    private boolean isOpenPeer;

    private Date evaDdl;

    private boolean isOverEvaDdl;
}
