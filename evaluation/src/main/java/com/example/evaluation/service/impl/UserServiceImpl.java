package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.evaluation.controller.dto.LoginDto;
import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.controller.dto.UpdateDto;
import com.example.evaluation.domain.Homework;
import com.example.evaluation.domain.User;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.UserMapper;
import com.example.evaluation.service.UserService;
import com.example.evaluation.utils.JwtUtil;
import com.example.evaluation.utils.LoginUser;
import com.example.evaluation.utils.RedisCache;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    public User getUserInfoByName(String name){
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getName,name);
        User one;
        try{
            one = getOne(wrapper);
        }catch (Exception e){
            log.error(e.toString());
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统错误");
        }return one;
    }

    public User getUserInfoByID(String uid){
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId,uid);
        User one;
        try{
            one = getOne(wrapper);
        }catch (Exception e){
            log.error(e.toString());
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(),"系统错误");
        }return one;
    }

    @Override
    public String getPermsByID(String id) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId,id);
        User one;
        try{
            one = getOne(wrapper);
        }catch (Exception e){
            log.error(e.toString());
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(),"系统错误");
        }return one.getPermission();
    }

    @Override
    @Transactional
    public HashMap<String,String> login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUname(),loginDto.getPwd());
        System.out.println("sssss");

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
        map.put("permission",loginUser.getUser().getPermission());
        return map;
    }

    @Override
    @Transactional
    public User register(RegisterDto rdto) {
        User one = getUserInfoByName(rdto.getUname());
        if(one != null)
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "用户名已存在");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setName(rdto.getUname());
        user.setPassword(passwordEncoder.encode(rdto.getPassword()));
        save(user);

        return getUserInfoByName(rdto.getUname());
    }
    @Override
    @Transactional
    public boolean deleteUserById(String uid) {
        User one = getUserInfoByID(uid);
        if(one == null) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"用户不存在");

        if(one.getName().equals("admin"))
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"超级管理员admin不允许删除");

        if(one.getPermission().equals("1"))
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "管理员不可删除,如需删除请修改权限后再删除");

        if(removeById(uid)) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(),"用户不存在");
    }

    @Override
    @Transactional
    public boolean updateUserInfo(UpdateDto ud) {
        String id = ud.getId();
        User one = getUserInfoByID(id);
        if(one == null)throw new ServiceException(HttpStatus.NOT_FOUND.value(),"用户不存在");

        if(one.getName().equals("admin"))
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"超级管理员admin不可更新");

        // TODO: 2023-11-02 objectmapper
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
//        String outJson = objectMapper.writeValueAsString(one);
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId,id)
                .set(User::getName,ud.getName())
                .set(User::getPermission,ud.getPermission())
                .set(User::getEmail,ud.getEmail());
        int flag = userMapper.update(null,wrapper);
        if(flag >= 1) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }

    @Override
    @Transactional
    public boolean updateUserName(String id,String newname) {
        User one = getUserInfoByName(newname);
        if(one != null)
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(),"该用户名已存在");

        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId,id).set(User::getName,newname);
        int flag = userMapper.update(null,wrapper);
        if(flag >= 1) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }

    @Override
    @Transactional
    public boolean updateUserPwd(String id,String oldpwd, String newpwd) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = getUserInfoByID(id);
        if(!passwordEncoder.matches(oldpwd,user.getPassword()))
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"原密码输入错误");
        if(newpwd.equals(oldpwd))
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"新密码与原密码相同");

        String token = passwordEncoder.encode(newpwd);

        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId,id).set(User::getPassword,token);

        int flag = userMapper.update(null,wrapper);
        if(flag >= 1) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }

    @Override
    @Transactional
    public boolean updateUserPer(String id, String newper) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId,id).set(User::getPermission,newper);
        int flag = userMapper.update(null,wrapper);
        if(flag >= 1) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }

    @Override
    @Transactional
    public String logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + userid);
        return "退出成功";
    }
}