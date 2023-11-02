package com.example.evaluation.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("my_file")
public class MyFile implements Serializable {

    @TableId(value = "fid",type= IdType.AUTO)
    private String fid;

    @TableField
//    @NotBlank(message = "课程id不能为空")
    private String fname;

    @TableField
//    @NotBlank(message = "课程id不能为空")
    private String type;

    @TableField
//    @NotBlank(message = "课程id不能为空")
    private Long size;

    @TableField
//    @NotBlank(message = "课程id不能为空")
    private String url;

    @TableField
//    @NotBlank(message = "课程id不能为空")
    private String md5;

    @TableField
//    @NotBlank(message = "课程id不能为空")
    private boolean isDelete;

    @TableField
//    @NotBlank(message = "课程id不能为空")
    //是否禁用链接
    private boolean enable;
}
