package com.example.evaluation.controller;

import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.entity.Result;
import com.example.evaluation.service.impl.StudentServiceImpl;
import com.example.evaluation.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherServiceImpl teacherService;

    //管理员注册教师用户
    @PostMapping("/register")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> register(@RequestBody @Valid RegisterDto rdto){
        return new ResponseEntity<>(Result.success(teacherService.register(rdto)), HttpStatus.OK);
    }

    //管理员查询所有学生信息
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> getAllInfo(){
        return new ResponseEntity<>(Result.success(teacherService.list()), HttpStatus.OK);
    }

    //管理员根据id查询学生信息
    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> getByID(@RequestParam @Valid String id){
        return new ResponseEntity<>(Result.success(teacherService.getById(id)), HttpStatus.OK);
    }

    //删除用户信息
    @PostMapping("/delete")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> deleteUserById(@RequestParam @Valid String id){
        return new ResponseEntity<>(Result.success(teacherService.removeById(id)), HttpStatus.OK);
    }
}
