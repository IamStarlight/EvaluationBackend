package com.example.evaluation.service;

import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.controller.dto.UpdateDto;
import com.example.evaluation.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public interface RoleService {

    void register(RegisterDto rdto);

    void updateUserInfo(UpdateDto ud);

    void deleteUserById(Integer id);

    void updateUserPwd(Integer id, String newpwd);

    void updateUserPwd(Integer id, String oldpwd,String newpwd);

}
