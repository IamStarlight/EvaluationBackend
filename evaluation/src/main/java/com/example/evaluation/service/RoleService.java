package com.example.evaluation.service;

import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.controller.dto.UpdateDto;
import com.example.evaluation.entity.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public interface RoleService {

    boolean register(RegisterDto rdto);

    boolean updateUserInfo(UpdateDto ud);
}
