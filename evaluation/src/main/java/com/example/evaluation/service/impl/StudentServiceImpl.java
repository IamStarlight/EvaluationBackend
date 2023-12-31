package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.controller.dto.UpdateDto;
import com.example.evaluation.entity.Student;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.StudentMapper;
import com.example.evaluation.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl
        extends ServiceImpl<StudentMapper, Student>
        implements RoleService {

    @Autowired
    private StudentMapper mapper;

    @Override
    public void register(RegisterDto rdto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Student one = new Student();
        one.setName(rdto.getName());
        one.setPassword(passwordEncoder.encode(rdto.getPassword()));
        one.setEmail(rdto.getEmail());
        save(one);
//        create trigger insert_stu
//        after insert on student for each row
//        begin
//        insert into user ( id,name,password,email,permission)
//        values( new.id,new.name,new.password,new.email, 'ROLE_STUDENT');
//        end;
    }

    @Override
    public void updateUserInfo(UpdateDto ud) {
        LambdaUpdateWrapper<Student> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Student::getId,ud.getId())
                .set(Student::getName,ud.getName())
                .set(Student::getEmail,ud.getEmail());
        int flag = mapper.update(null,wrapper);
        if(flag < 1) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
        }
//        create trigger update_stu
//        after update on student for each row
//        begin
//        update user
//        set name=new.name,password=new.password,email=new.email
//        where id=old.id;
//        end;
    }

    @Override
    public void updateUserPwd(Integer id, String newpwd) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        changePassword(id, newpwd, passwordEncoder);
    }

    @Override
    public void updateUserPwd(Integer id, String oldpwd, String newpwd) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Student user = getById(id);
        if(!passwordEncoder.matches(oldpwd,user.getPassword()))
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"原密码输入错误");
        if(newpwd.equals(oldpwd))
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"新密码与原密码相同");

        changePassword(id, newpwd, passwordEncoder);
    }

    private void changePassword(Integer id, String newpwd, BCryptPasswordEncoder passwordEncoder) {
        String token = passwordEncoder.encode(newpwd);

        LambdaUpdateWrapper<Student> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Student::getId,id).set(Student::getPassword,token);

        int flag = mapper.update(null,wrapper);
        if(flag < 1) throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }

    @Override
    public void deleteUserById(Integer id) {
        if(!removeById(id)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"用户不存在");
        }
//        create trigger delete_stu
//        after delete on student for each row
//        begin
//        delete from user where id=old.id;
//        end;
    }
}
