package com.example.evaluation.controller;

import com.example.evaluation.annotation.CurrentUser;
import com.example.evaluation.controller.dto.LoginDto;
import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.controller.dto.UpdateDto;
import com.example.evaluation.domain.Result;
import com.example.evaluation.domain.User;
import com.example.evaluation.utils.LoginUser;
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

    //管理员查询所有用户信息 ok
    @GetMapping("/all/student")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> getAllUserInfo(){
        return new ResponseEntity<>(Result.success(userService.list()), HttpStatus.OK);
    }

    // TODO: 2023-11-01 allTeacher

    //管理员根据id查询用户信息 ok
    @GetMapping("/info/id")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> getStuByID(@RequestParam @Valid String uid){
        return new ResponseEntity<>(Result.success(userService.getById(uid)), HttpStatus.OK);
    }

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
    @PostMapping("/update/password")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> updateUserPwd(@RequestParam String id,
                                                @RequestParam
                                                @Valid @NotBlank(message = "新密码不能为空")
                                                String newpwd){
        return new ResponseEntity<>(Result.success(userService.updateUserPwd(id,newpwd)), HttpStatus.OK);
    }

    //用户修改自己的密码 ok
    @PostMapping("/change/password")
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
