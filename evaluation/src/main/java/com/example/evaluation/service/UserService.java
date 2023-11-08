package com.example.evaluation.service;

import com.example.evaluation.controller.dto.LoginDto;
import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.controller.dto.UpdateDto;
import com.example.evaluation.entity.User;
import com.example.evaluation.enums.PerEnum;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;

@Service
public interface UserService {

    HashMap<String,String> login(LoginDto loginDto);

    String getPermsById(Integer id);

    void logout();

    void register(RegisterDto d);

    void updateUserPwd(Integer id, String newpwd);

    void updateUserPwd(Integer id,String oldpwd,String newpwd);

    Object getAllRoleInfo(String permission);

    Object getOneByID(Integer id);

    void updateUserInfo(UpdateDto d);

    void deleteUserById(Integer id, String permission);
}
