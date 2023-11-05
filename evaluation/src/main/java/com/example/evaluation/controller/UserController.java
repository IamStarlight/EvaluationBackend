package com.example.evaluation.controller;

import com.example.evaluation.annotation.CurrentUser;
import com.example.evaluation.controller.dto.LoginDto;
import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.controller.dto.UpdateDto;
import com.example.evaluation.entity.Result;
import com.example.evaluation.entity.Teacher;
import com.example.evaluation.entity.User;
import com.example.evaluation.service.impl.AdminServiceImpl;
import com.example.evaluation.service.impl.StudentServiceImpl;
import com.example.evaluation.service.impl.TeacherServiceImpl;
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

    @CurrentUser User user;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StudentServiceImpl studentService;

    //登录用户 ok
    @PostMapping("/user/login")
    public ResponseEntity<Result> login(@RequestBody @Valid LoginDto LoginDto){
        return new ResponseEntity<>(Result.success(userService.login(LoginDto)), HttpStatus.OK);
    }

    //登出用户 ok
    @PostMapping("/user/logout")
    public ResponseEntity<Result> logout(){
        return new ResponseEntity<>(Result.success(userService.logout()), HttpStatus.OK);
    }

    //获取登陆用户信息
    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('ROLE_AMDIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> getStuByID(){
        return new ResponseEntity<>(Result.success(user), HttpStatus.OK);
    }

    //管理员更改用户密码 ok
    // TODO: 2023-11-06 权限问题 
//    @PostMapping("/password/admin")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
//    public ResponseEntity<Result> updateUserPwd(@RequestParam Integer id,
//                                                @RequestParam
//                                                @Valid @NotBlank(message = "新密码不能为空")
//                                                String newpwd){
//        return new ResponseEntity<>(Result.success(userService.updateUserPwd(id,newpwd)), HttpStatus.OK);
//    }

    //用户修改自己的密码 ok
    // TODO: 2023-11-06 权限问题 
//    @PostMapping("/password/my")
//    @PreAuthorize("hasAnyAuthority('ROLE_AMDIN','ROLE_TEACHER','ROLE_STUDENT')")
//    public ResponseEntity<Result> updateMyPwd(@CurrentUser User user,
//                                                @RequestParam
//                                                @Valid @NotBlank(message = "原密码不能为空")
//                                                String oldpwd,
//                                                @RequestParam
//                                                @Valid @NotBlank(message = "新密码不能为空")
//                                                String newpwd){
//        return new ResponseEntity<>(Result.success(userService.updateUserPwd(user.getId(),oldpwd,newpwd)), HttpStatus.OK);
//    }


}
