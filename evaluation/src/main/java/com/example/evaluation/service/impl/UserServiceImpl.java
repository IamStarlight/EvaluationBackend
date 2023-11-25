package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.evaluation.annotation.CurrentUser;
import com.example.evaluation.controller.dto.LoginDto;
import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.controller.dto.UpdateDto;
import com.example.evaluation.entity.User;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.UserMapper;
import com.example.evaluation.server.SseEmitterServer;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            throw new ServiceException(HttpStatus.UNAUTHORIZED.value(), "用户名或密码错误");

        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
//        System.out.println("!!!!!!loginuser in login: "+loginUser.getUser().getId()+", "+loginUser.getUser().getName());

        //把token响应给前端
        HashMap<String,String> map = new HashMap<>();
        map.put("token",jwt);
        map.put("permission",loginUser.getUser().getPermission());

//        SseEmitterServer.connect(userId +loginUser.getUser().getName());

        return map;
    }

    @Override
    @Transactional
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userid = String.valueOf(loginUser.getUser().getId());
        redisCache.deleteObject("login:" + userid);
    }

    @Override
    public void register(RegisterDto d) {
        switch (d.getPermission()) {
            case "1":
                adminService.register(d);
                break;
            case "2":
                teacherService.register(d);
                break;
            case "3":
                studentService.register(d);
                break;
            default:
                throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "用户类型不存在");
        }
    }

    @Override
    public void updateUserPwd(Integer id, String newpwd) {
        User one = getById(id);
        switch (one.getPermission()) {
            case "ROLE_STUDENT":
                studentService.updateUserPwd(id, newpwd);
                break;
            case "ROLE_TEACHER":
                teacherService.updateUserPwd(id, newpwd);
                break;
            case "ROLE_ADMIN":
                adminService.updateUserPwd(id, newpwd);
                break;
            default:
                throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "用户类型不存在");
        }
    }

    @Override
    public void updateUserPwd(Integer id, String oldpwd, String newpwd) {
        User one = getById(id);
        switch (one.getPermission()) {
            case "ROLE_STUDENT":
                studentService.updateUserPwd(id, oldpwd, newpwd);
                break;
            case "ROLE_TEACHER":
                teacherService.updateUserPwd(id, oldpwd, newpwd);
                break;
            case "ROLE_ADMIN":
                adminService.updateUserPwd(id, oldpwd, newpwd);
                break;
            default:
                throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "用户类型不存在");
        }
    }

    @Override
    public Object getAllRoleInfo(String permission) {
//        System.out.println("!!!!!!!!!进UserServiceImpl了："+permission);
        switch (permission) {
            case "1":
                return adminService.list();
            case "2":
                return teacherService.list();
            case "3":
                return studentService.list();
            default:
                throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "用户类型不存在");
        }
    }

    @Override
    public Object getOneByID(Integer id, String permission) {
        switch (permission) {
            case "1":
                return adminService.getById(id);
            case "2":
                return teacherService.getById(id);
            case "3":
                return studentService.getById(id);
            default:
                return getById(id);
        }
    }

    @Override
    public void updateUserInfo(UpdateDto d) {
        switch (d.getPermission()) {
            case "1":
                adminService.updateUserInfo(d);
                break;
            case "2":
                teacherService.updateUserInfo(d);
                break;
            case "3":
                studentService.updateUserInfo(d);
                break;
            default:
                throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "用户类型不存在");
        }
    }

    @Override
    public void deleteUserById(Integer myId,Integer id, String permission) {
        switch (permission) {
            case "1":
                adminService.deleteUserByIdNotMe(myId,id);
                break;
            case "2":
                teacherService.deleteUserById(id);
                break;
            case "3":
                studentService.deleteUserById(id);
                break;
            default:
                throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "用户类型不存在");
        }
    }
}