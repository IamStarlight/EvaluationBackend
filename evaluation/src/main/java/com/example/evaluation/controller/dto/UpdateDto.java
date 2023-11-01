package com.example.evaluation.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UpdateDto {

    @NotBlank(message = "id不能为空")
    private String id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "权限不能为空")
    private String permission;

    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "邮箱不能为空")
    private String email;
}
