package com.example.evaluation.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("teacher_user")
public class Teacher implements Serializable {

    @TableId(value = "tid",type=IdType.AUTO)
    private String Tid;

    @TableField(value = "tname")
    @NotBlank(message = "用户名不能为空")
    private String Tname;

    @TableField(value = "password")
    @NotBlank(message = "密码不能为空")
    private String Password;

    @TableField(value = "permission")
    @NotBlank(message = "权限不能为空")
    private String Permission;

    @TableField(value = "email")
    @javax.validation.constraints.Email(message = "邮箱格式不正确")
    @NotBlank(message = "权限不能为空")
    private String Email;
}
