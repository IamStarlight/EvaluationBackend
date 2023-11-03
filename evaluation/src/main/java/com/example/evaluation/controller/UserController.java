package com.example.evaluation.controller;

import com.example.evaluation.annotation.CurrentUser;
import com.example.evaluation.controller.dto.LoginDto;
import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.controller.dto.UpdateDto;
import com.example.evaluation.entity.Result;
import com.example.evaluation.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.evaluation.service.impl.UserServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    //登录用户 ok
    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestBody @Valid LoginDto LoginDto){
        return new ResponseEntity<>(Result.success(userService.login(LoginDto)), HttpStatus.OK);
    }

    //登出用户 ok
    @PostMapping("/logout")
    public ResponseEntity<Result> logout(){
        return new ResponseEntity<>(Result.success(userService.logout()), HttpStatus.OK);
    }

    //管理员注册用户 ok
    @PostMapping("/register")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> register(@RequestBody @Valid RegisterDto registerDto){
        return new ResponseEntity<>(Result.success(userService.register(registerDto)), HttpStatus.OK);
    }

    //管理员批量注册用户
//    @PostMapping("/register/batch")
//    @PreAuthorize("hasAuthority('1')")
//    public ResponseEntity<Result> registerBatch(){
//        return new ResponseEntity<>(Result.success(userService.registerBatch()), HttpStatus.OK);
//    }

    //管理员查询所有学生信息 ok
    @GetMapping("/all/student")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> getAllStudentInfo(){
        return new ResponseEntity<>(Result.success(userService.list()), HttpStatus.OK);
    }

    // TODO: 2023-11-01 allTeacher
//    @GetMapping("/all/student")
//    @PreAuthorize("hasAuthority('1')")
//    public ResponseEntity<Result> getAllTeacherInfo(){
//        return new ResponseEntity<>(Result.success(userService.list()), HttpStatus.OK);
//    }

    //管理员根据id查询学生信息 ok
    @GetMapping("/info/id")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> getStuByID(@RequestParam @Valid String uid){
        return new ResponseEntity<>(Result.success(userService.getById(uid)), HttpStatus.OK);
    }

    // TODO: 2023-11-03 管理员根据tid查询所有教师信息 

    //获取登陆用户信息
    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('1','2','3')")
    public ResponseEntity<Result> getStuByID(@CurrentUser User user){
        return new ResponseEntity<>(Result.success(user), HttpStatus.OK);
    }

    // TODO: 2023-11-02  管理员更新用户数据 ok objectMapper
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> updateUserInfo(@RequestBody @Valid UpdateDto ud){
        return new ResponseEntity<>(Result.success(userService.updateUserInfo(ud)), HttpStatus.OK);
    }

    //管理员更改用户密码 ok
    @PostMapping("/password/admin")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> updateUserPwd(@RequestParam String id,
                                                @RequestParam
                                                @Valid @NotBlank(message = "新密码不能为空")
                                                String newpwd){
        return new ResponseEntity<>(Result.success(userService.updateUserPwd(id,newpwd)), HttpStatus.OK);
    }

    //用户修改自己的密码 ok
    @PostMapping("/password/user")
    @PreAuthorize("hasAnyAuthority('1','2','3')")
    public ResponseEntity<Result> updateMyPwd(@CurrentUser User user,
                                                @RequestParam
                                                @Valid @NotBlank(message = "原密码不能为空")
                                                String oldpwd,
                                                @RequestParam
                                                @Valid @NotBlank(message = "新密码不能为空")
                                                String newpwd){
        return new ResponseEntity<>(Result.success(userService.updateUserPwd(user.getId(),oldpwd,newpwd)), HttpStatus.OK);
    }

    //删除用户信息 ok
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> deleteUserById(@RequestParam @Valid String uid){
        return new ResponseEntity<>(Result.success(userService.deleteUserById(uid)), HttpStatus.OK);
    }
}
