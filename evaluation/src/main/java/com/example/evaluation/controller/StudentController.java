package com.example.evaluation.controller;

import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.controller.dto.UpdateDto;
import com.example.evaluation.entity.Result;
import com.example.evaluation.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@Validated
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    //管理员注册学生用户
    @PostMapping("/register")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> register(@RequestBody @Valid RegisterDto rdto){
        return new ResponseEntity<>(Result.success(studentService.register(rdto)), HttpStatus.OK);
    }

    //管理员查询所有学生信息
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> getAllStudentInfo(){
        return new ResponseEntity<>(Result.success(studentService.list()), HttpStatus.OK);
    }

    //管理员根据id查询学生信息
    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> getStuByID(@RequestParam @Valid String id){
        return new ResponseEntity<>(Result.success(studentService.getById(id)), HttpStatus.OK);
    }

    // TODO: 2023-11-02  管理员更新用户数据 ok objectMapper
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> updateUserInfo(@RequestBody @Valid UpdateDto ud){
        return new ResponseEntity<>(Result.success(studentService.updateUserInfo(ud)), HttpStatus.OK);
    }

    //删除用户信息
    @PostMapping("/delete")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> deleteUserById(@RequestParam @Valid String id){
        return new ResponseEntity<>(Result.success(studentService.removeById(id)), HttpStatus.OK);
    }


}
