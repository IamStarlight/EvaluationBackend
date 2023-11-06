package com.example.evaluation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.evaluation.enums.PerEnum;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User implements Serializable {

    @TableId
    private Integer id;

    @TableField
    private String name;

    @TableField
    private String password;

    @TableField
    private String email;

    @TableField
    private String permission;
}
