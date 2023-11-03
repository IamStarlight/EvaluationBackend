package com.example.evaluation.entity;

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
@TableName("student")
//@EntityScan
public class User implements Serializable {

    @TableId(value = "sid",type=IdType.AUTO)
    private String id;

    @TableField(value = "sname")
    @NotBlank(message = "用户名不能为空")
    private String name;

    @TableField
    @NotBlank(message = "密码不能为空")
    private String password;

    @TableField
    @NotBlank(message = "权限不能为空")
    private String permission;

    @TableField
    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "权限不能为空")
    private String email;

}
