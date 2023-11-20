package com.example.evaluation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.evaluation.enums.SubmitStatusEnum;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("stu_homework")
public class StuWork implements Serializable {

    @MppMultiId
    @TableField
    private Integer sid;

    @MppMultiId
    @TableField
    private Integer wid;

    @MppMultiId
    @TableField
    private Integer cid;

    @TableField
    private String details;

    @TableField
    private String url;

    @TableField
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date submitTime;

    @TableField
    private Integer totalGrade;

    @TableField
    private Integer teacherGrade;

    @TableField
    private String teacherComments;

    @TableField
    private Integer peerGrade;

    @TableField
    private SubmitStatusEnum submitStatus;

    @TableField
    private boolean isRead;

    @TableField
    private boolean isPeer;

    @TableField
    private boolean isAppeal;

    @TableField
    private String appealReason;

    @TableField
    private String appealReply;

}
