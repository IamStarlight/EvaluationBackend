package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.controller.dto.UpdateDto;
import com.example.evaluation.entity.Admin;
import com.example.evaluation.entity.Student;
import com.example.evaluation.entity.Teacher;
import com.example.evaluation.entity.User;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.AdminMapper;
import com.example.evaluation.mapper.TeacherMapper;
import com.example.evaluation.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl
        extends ServiceImpl<AdminMapper, Admin>
        implements RoleService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean register(RegisterDto rdto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Admin one = new Admin();
        one.setName(rdto.getName());
        one.setPassword(passwordEncoder.encode(rdto.getPassword()));
        one.setEmail(rdto.getEmail());
        return save(one);
    }

    @Override
    public boolean updateUserInfo(UpdateDto ud) {
        LambdaUpdateWrapper<Admin> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId,ud.getId())
                .set(User::getName,ud.getName())
                .set(User::getPermission,ud.getPermission())
                .set(User::getEmail,ud.getEmail());
        int flag = adminMapper.update(null,wrapper);
        if(flag >= 1) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }
}
