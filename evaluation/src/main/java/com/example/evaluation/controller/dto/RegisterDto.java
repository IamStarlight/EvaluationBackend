package com.example.evaluation.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterDto {
    @NotBlank(message = "用户名不能为空")
    private String uname;

    @NotBlank(message = "密码不能为空")
    private String password;

    //可以为空
    private String permission;
}
