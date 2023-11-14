package com.example.evaluation.controller;

import com.example.evaluation.annotation.CurrentUser;
import com.example.evaluation.controller.dto.LoginDto;
import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.controller.dto.UpdateDto;
import com.example.evaluation.entity.Result;
import com.example.evaluation.entity.User;
import com.example.evaluation.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.evaluation.service.impl.UserServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

// TODO: 2023-11-06 validation 

@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl service;

//--------PostMapping------------------------------------

    //登录用户 ok
    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestBody @Valid LoginDto LoginDto){
        return new ResponseEntity<>(Result.success(service.login(LoginDto)), HttpStatus.OK);
    }

    //登出用户 ok
    @PostMapping("/logout")
    public ResponseEntity<Result> logout(){
        service.logout();
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    //管理员注册用户 ok
    @PostMapping("/register")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> register(@RequestBody @Valid RegisterDto d){
        service.register(d);
        return new ResponseEntity<>((Result.success()),HttpStatus.OK);
    }

//--------PutMapping------------------------------------

    //管理员更新用户数据
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> updateUserInfo(@RequestBody @Valid UpdateDto d){
        service.updateUserInfo(d);
        return new ResponseEntity<>((Result.success()),HttpStatus.OK);
    }

    //管理员更改用户密码 ok
    @PutMapping("/password")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> updateUserPwd(@RequestParam @Valid @NotNull(message = "id不能为空")
                                                Integer id,
                                                @RequestParam @Valid @NotBlank(message = "新密码不能为空")
                                                String newpwd){
        service.updateUserPwd(id,newpwd);
        return new ResponseEntity<>((Result.success()),HttpStatus.OK);
    }

    //用户修改自己的密码 ok 跳转登陆界面重新登陆
    @PutMapping("/mypassword")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> updateMyPwd(@CurrentUser User user,
                                              @RequestParam @Valid @NotBlank(message = "原密码不能为空")
                                              String oldpwd,
                                              @RequestParam @Valid @NotBlank(message = "新密码不能为空")
                                              String newpwd){
        service.updateUserPwd(user.getId(),oldpwd,newpwd);
        return new ResponseEntity<>((Result.success()),HttpStatus.OK);
    }

//--------GetMapping------------------------------------

    //获取登陆用户信息 ok
    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> getUserByID(@CurrentUser User user){
        if(user==null)
            throw new ServiceException(HttpStatus.NO_CONTENT.value(), "User为空");
        return new ResponseEntity<>(Result.success(user), HttpStatus.OK);

    }

    //管理员查询所有学生or老师or管理员信息 ok
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> getAllRoleInfo(@RequestParam @Valid @NotBlank(message = "权限不能为空")
                                                 String permission){
        return new ResponseEntity<>(Result.success(service.getAllRoleInfo(permission)), HttpStatus.OK);
    }

    //管理员根据id查询用户信息
    @GetMapping("/oneinfo")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> getOneByID(@RequestParam @Valid @NotNull(message = "id不能为空")
                                             Integer id,
                                             @RequestParam
                                             String permission){
        return new ResponseEntity<>(Result.success(service.getOneByID(id,permission)), HttpStatus.OK);
    }

//--------DeleteMapping------------------------------------

    //删除用户信息
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> deleteUserById(@CurrentUser User user,
                                                 @RequestParam @Valid @NotNull(message = "id不能为空")
                                                     Integer id,
                                                 @RequestParam @Valid @NotBlank(message = "权限不能为空")
                                                    String permission){
        service.deleteUserById(user.getId(),id,permission);
        return new ResponseEntity<>((Result.success()),HttpStatus.OK);
    }
}
