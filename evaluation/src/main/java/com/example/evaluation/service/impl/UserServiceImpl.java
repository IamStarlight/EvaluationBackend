package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.evaluation.controller.dto.LoginDto;
import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.controller.dto.UpdateDto;
import com.example.evaluation.entity.Student;
import com.example.evaluation.entity.User;
import com.example.evaluation.enums.PerEnum;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.UserMapper;
import com.example.evaluation.service.UserService;
import com.example.evaluation.utils.JwtUtil;
import com.example.evaluation.utils.LoginUser;
import com.example.evaluation.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Objects;

@Service
public class UserServiceImpl
        extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private TeacherServiceImpl teacherService;

    @Autowired
    private AdminServiceImpl adminService;

    @Override
    public String getPermsById(Integer id) {
        return mapper.getPermsById(id);
    }

    @Override
    @Transactional
    public HashMap<String,String> login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if(Objects.isNull(authenticate))
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "用户名或密码错误");

        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);

        //把token响应给前端
        HashMap<String,String> map = new HashMap<>();
        map.put("token",jwt);
//        map.put("permission",loginUser.getUser().getPermission());
        return map;
    }

    @Override
    @Transactional
    public String logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userid = String.valueOf(loginUser.getUser().getId());
        redisCache.deleteObject("login:" + userid);
        return "退出成功";
    }

    @Override
    public boolean updateUserPwd(Integer id, String newpwd) {
        User one = getById(id);
        switch (one.getPermission()) {
            case "ROLE_STUDENT":
                return studentService.updateUserPwd(id, newpwd);
            case "ROLE_TEACHER":
                return teacherService.updateUserPwd(id, newpwd);
            case "ROLE_ADMIN":
                return adminService.updateUserPwd(id, newpwd);
            default:
                return false;
        }
    }

    @Override
    public boolean updateUserPwd(Integer id, String oldpwd, String newpwd) {
        User one = getById(id);
        switch (one.getPermission()) {
            case "ROLE_STUDENT":
                return studentService.updateUserPwd(id, oldpwd, newpwd);
            case "ROLE_TEACHER":
                return teacherService.updateUserPwd(id, oldpwd, newpwd);
            case "ROLE_ADMIN":
                return adminService.updateUserPwd(id, oldpwd, newpwd);
            default:
                return false;
        }
    }
}