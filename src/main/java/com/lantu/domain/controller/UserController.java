package com.lantu.domain.controller;

import com.lantu.common.vo.Result;
import com.lantu.domain.entity.User;
import com.lantu.domain.mapper.UserMapper;
import com.lantu.domain.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/domain/user")
public class UserController {
    @Autowired
    private IUserService userService;
    private UserMapper userMapper;


    //登录
    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody User user){
        Map<String,Object> data =  userService.login(user);
        if(data != null){
            return Result.success(data);
        }
        return Result.error(20001,"用户名或密码错误");

    }

    //个人获得自己的信息
    @GetMapping("/info")
    public Result<User> getUserInfo(@RequestHeader("Token") String token){
        //根据token来获取redis的登录信息
        User data =  userService.getUserInfo(token);
        if (data != null){
            return Result.success(data);
        }
        return Result.error(20002,"用户登录信息失效");
    }

    //个人修改自己的密码
    @ PostMapping("/changePassword")
    public  Result<Object> changePassword(@RequestBody User user,@RequestHeader("Token") String token){
        boolean a = userService.changePassword(user,token);
        if(a){
            return Result.success();
        }
        return Result.error(20006,"修改失败");
    }

    //个人修改自己的邮箱
    @ PostMapping("/changeEmail")
    public  Result<Object> changeEmail(@RequestBody User user,@RequestHeader("Token") String token){
        boolean a = userService.changeEmail(user,token);
        if(a){
            return Result.success();
        }
        return Result.error(20006,"修改失败");
    }

    @GetMapping("/specinfo")
    public Result<List<User>> getUserInfo(@RequestParam("id") int id){
        //根据token来获取redis的登录信息
        List<User> data =  userService.getSpecUserInfo(id);
        if (data != null){
            return Result.success(data);
        }
        return Result.error(20002,"用户登录信息失效");
    }

    //登出
    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("Token") String token){
        userService.logout(token);
        return Result.success();
    }

    //管理员查看所有用户信息
    @GetMapping("/all")
    public Result<List<User>> getAllUser(){
        List<User>list = userService.list();
        return Result.success(list);
    }

    //管理员查看所有学生信息
    @GetMapping("/allStudent")
    public Result<List<User>> getAllStudent(){
        List<User>list = userMapper.listStudent();
        return Result.success(list);
    }

    //管理员查看所有教师信息
    @GetMapping("/allTeacher")
    public Result<List<User>> getAllTeacher(){
        List<User>list = userMapper.listTeacher();
        return Result.success(list);
    }

    //管理员增加用户
    @PostMapping("/addUser")
    public  Result<Object> addUser(@RequestBody User user){
        boolean a = userService.addUser(user);
        if(a){
            return Result.success();
        }
        return Result.error(20004,"插入失败");
    }

    //管理员删除用户
    @PostMapping("/removeUser")
    public  Result<Object> removeUser(@RequestBody User user){
        boolean a = userService.removeUser(user);
        if(a){
            return Result.success();
        }
        return Result.error(20005,"删除失败");
    }

    //管理员修改用户
    @PostMapping("/changeUser")
    public  Result<Object> changeUser(@RequestBody User user){
        boolean a = userService.changeUser(user);
        if(a){
            return Result.success();
        }
        return Result.error(20005,"修改失败");
    }


    @ PostMapping("/changeUserface")
    public  Result<Object> changeUserface(@RequestBody User user,@RequestHeader("Token") String token){
        boolean a = userService.changeUserface(user,token);
        if(a){
            return Result.success();
        }
        return Result.error(20006,"修改失败");
    }

    @ GetMapping("/getUploadToken")
    public  Result<String> getUploadToken(){
        String token = userService.returnUploadToken();
            return Result.success(token);

    }

    @ PostMapping("/alterUser")
    public  Result<Object> alterUser(@RequestBody User user){
        boolean a = userService.alterUser(user);
        if(a){
            return Result.success();
        }
        return Result.error(20006,"修改失败");
    }




}
