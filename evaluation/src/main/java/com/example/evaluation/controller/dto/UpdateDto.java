package com.example.evaluation.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UpdateDto {

    @NotBlank(message = "id不能为空")
    private String id;

    private String name;

    private String permission;
}
