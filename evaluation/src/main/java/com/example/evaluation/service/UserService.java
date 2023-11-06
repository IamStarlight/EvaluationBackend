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

    String logout();

    boolean updateUserPwd(Integer id, String newpwd);

    boolean updateUserPwd(Integer id,String oldpwd,String newpwd);

}
