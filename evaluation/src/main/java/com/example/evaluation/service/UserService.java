package com.example.evaluation.service;

import com.example.evaluation.controller.dto.LoginDto;
import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.controller.dto.UpdateDto;
import com.example.evaluation.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface UserService {

    User getUserInfoByName(String name);

    HashMap<String,String> login(LoginDto loginDto);

    User register(RegisterDto registerDto);

    String getPermsByID(String id);

    boolean deleteUserById(String uid);

    boolean updateUserInfo(UpdateDto ud);

    boolean updateUserName(String id,String name);

    boolean updateUserPwd(String id,String newpwd);

    boolean updateUserPwd(String id,String oldpwd, String newpwd);

    boolean updateUserPer(String id,String per);

    String logout();
}
