package com.example.evaluation.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("peer_evaluation")
public class PeerEva implements Serializable {

    @MppMultiId
    @TableField
    @NotNull(message = "评价学生id不能为空")
    private Integer evaSid;

    @MppMultiId
    @TableField
    @NotNull(message = "被评学生id不能为空")
    private Integer sid;

    @MppMultiId
    @TableField
    @NotNull(message = "作业id不能为空")
    private Integer wid;

    @MppMultiId
    @TableField
    @NotNull(message = "课程id不能为空")
    private Integer cid;

    @TableField
    @NotNull(message = "评分不能为空")
    private Integer grade;

    @TableField
    private String comments;
}
