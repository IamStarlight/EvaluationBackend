package com.example.evaluation.controller.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.evaluation.enums.StatusEnum;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class HomeworkInfo {

    private Integer wid;

    private Integer cid;

    private String cname;

    private String title;

    private String details;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;

    private String status;

    private Integer submitNumber;

    private String url;

    private Integer totalGrade;

    private boolean isRead;

    private boolean isEva;

    private boolean isAppeal;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date appealTime;

    private boolean isSubmit;

    private boolean isOverEvaDdl;

}
