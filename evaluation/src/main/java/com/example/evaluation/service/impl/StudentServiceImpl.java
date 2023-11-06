package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.controller.dto.UpdateDto;
import com.example.evaluation.entity.Student;
import com.example.evaluation.entity.User;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.StudentMapper;
import com.example.evaluation.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class StudentServiceImpl
        extends ServiceImpl<StudentMapper, Student>
        implements RoleService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public boolean register(RegisterDto rdto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Student one = new Student();
        one.setName(rdto.getName());
        one.setPassword(passwordEncoder.encode(rdto.getPassword()));
        one.setEmail(rdto.getEmail());
        return save(one);
    }

    @Override
    public boolean updateUserInfo(UpdateDto ud) {
        LambdaUpdateWrapper<Student> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Student::getId,ud.getId())
                .set(Student::getName,ud.getName())
                .set(Student::getEmail,ud.getEmail());
        int flag = studentMapper.update(null,wrapper);
        if(flag >= 1) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }

    @Override
    public boolean updateUserPwd(Integer id, String newpwd) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return changePassword(id, newpwd, passwordEncoder);
    }

    @Override
    public boolean updateUserPwd(Integer id, String oldpwd, String newpwd) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Student user = getById(id);
        if(!passwordEncoder.matches(oldpwd,user.getPassword()))
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"原密码输入错误");
        if(newpwd.equals(oldpwd))
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"新密码与原密码相同");

        return changePassword(id, newpwd, passwordEncoder);
    }

    private boolean changePassword(Integer id, String newpwd, BCryptPasswordEncoder passwordEncoder) {
        String token = passwordEncoder.encode(newpwd);

        LambdaUpdateWrapper<Student> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Student::getId,id).set(Student::getPassword,token);

        int flag = studentMapper.update(null,wrapper);
        if(flag >= 1) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }

    @Override
    public boolean deleteUserById(Integer id) {
        if(removeById(id)) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(),"用户不存在");
    }
}
