package com.example.evaluation.controller;

import com.example.evaluation.controller.dto.LoginDto;
import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.controller.dto.UpdateDto;
import com.example.evaluation.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.evaluation.service.impl.UserServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;


@CrossOrigin
@RestController
@Validated
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    //登录用户
    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestBody @Valid LoginDto LoginDto){

        return new ResponseEntity<>(Result.success(userService.login(LoginDto)), HttpStatus.OK);
    }

    //登出用户
    @PostMapping("/logout")
    public ResponseEntity<Result> logout(){
        return new ResponseEntity<>(Result.success(userService.logout()), HttpStatus.OK);
    }

    //注册用户
    // TODO: 2023-11-01 500 Internal Server Error
    @PostMapping("/register")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> userRegister(@RequestBody @Valid RegisterDto registerDto){
        return new ResponseEntity<>(Result.success(userService.register(registerDto)), HttpStatus.OK);
    }

    //查询所有用户信息
    @GetMapping("/all/student")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> getAllUserInfo(){
        return new ResponseEntity<>(Result.success(userService.list()), HttpStatus.OK);
    }

    // TODO: 2023-11-01 allTeacher

    //根据id查询用户信息
    @GetMapping("/info")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> getStuByID(@RequestParam @Valid String uid){
        return new ResponseEntity<>(Result.success(userService.getById(uid)), HttpStatus.OK);
    }

    //更新用户数据
    // TODO: 2023-11-01 独立更改
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> updateUserInfo(@RequestBody @Valid UpdateDto ud){
        return new ResponseEntity<>(Result.success(userService.updateUserInfo(ud)), HttpStatus.OK);
    }

    //更改用户密码
    // TODO: 2023-11-01 500
    @PostMapping("/update/password")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> updateUserPwd(@RequestParam String id,
                                                @RequestParam
                                                @Valid @NotBlank(message = "原密码不能为空")
                                                String oldpwd,
                                                @RequestParam
                                                @Valid @NotBlank(message = "新密码不能为空")
                                                String newpwd){
        return new ResponseEntity<>(Result.success(userService.updateUserPwd(id,oldpwd,newpwd)), HttpStatus.OK);
    }

    //删除用户信息
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> deleteUserById(@RequestParam @Valid String uid){
        return new ResponseEntity<>(Result.success(userService.deleteUserById(uid)), HttpStatus.OK);
    }





}
