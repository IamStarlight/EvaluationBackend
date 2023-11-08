package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.annotation.CurrentUser;
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

    @CurrentUser User user;

    @Override
    public void register(RegisterDto rdto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Admin one = new Admin();
        one.setName(rdto.getName());
        one.setPassword(passwordEncoder.encode(rdto.getPassword()));
        one.setEmail(rdto.getEmail());
        save(one);
    }

    @Override
    public void updateUserInfo(UpdateDto ud) {
        LambdaUpdateWrapper<Admin> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Admin::getId,ud.getId())
                .set(Admin::getName,ud.getName())
                .set(Admin::getEmail,ud.getEmail());
        int flag = adminMapper.update(null,wrapper);
        if(flag < 1) throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }

    @Override
    public void updateUserPwd(Integer id, String newpwd) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        changePassword(id, newpwd, passwordEncoder);
    }

    @Override
    public void updateUserPwd(Integer id, String oldpwd, String newpwd) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Admin user = getById(id);
        if(!passwordEncoder.matches(oldpwd,user.getPassword()))
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"原密码输入错误");
        if(newpwd.equals(oldpwd))
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"新密码与原密码相同");

        changePassword(id, newpwd, passwordEncoder);
    }

    private void changePassword(Integer id, String newpwd, BCryptPasswordEncoder passwordEncoder) {
        String token = passwordEncoder.encode(newpwd);

        LambdaUpdateWrapper<Admin> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Admin::getId,id).set(Admin::getPassword,token);

        int flag = adminMapper.update(null,wrapper);
        if(flag < 1) throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }

    @Override
    public void deleteUserById(Integer id) {
        Admin one = getById(id);
        if(one.getId().equals(user.getId()))
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"不能删除自己的账号");

        if(!removeById(id)) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"用户不存在");
    }
}