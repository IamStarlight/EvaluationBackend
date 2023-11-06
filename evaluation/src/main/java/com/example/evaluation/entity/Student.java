package com.example.evaluation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("student")
public class Student implements Serializable {

    @TableId(type= IdType.AUTO)
    private Integer id;

    @TableField
    private String name;

    @TableField
    private String password;

    @TableField
    private String email;

}
