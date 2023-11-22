package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.annotation.CurrentUser;
import com.example.evaluation.controller.dto.RegisterDto;
import com.example.evaluation.controller.dto.UpdateDto;
import com.example.evaluation.entity.Admin;
import com.example.evaluation.entity.User;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.AdminMapper;
import com.example.evaluation.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminServiceImpl
        extends ServiceImpl<AdminMapper, Admin>
        implements RoleService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void register(RegisterDto rdto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Admin one = new Admin();
        one.setName(rdto.getName());
        one.setPassword(passwordEncoder.encode(rdto.getPassword()));
        one.setEmail(rdto.getEmail());
        save(one);
//        create trigger insert_admin
//        after insert on admin for each row
//        begin
//        insert into user ( id,name,password,email,permission)
//        values( new.id,new.name,new.password,new.email, 'ROLE_ADMIN');
//        end;
    }

    @Override
    public void updateUserInfo(UpdateDto ud) {
        LambdaUpdateWrapper<Admin> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Admin::getId,ud.getId())
                .set(Admin::getName,ud.getName())
                .set(Admin::getEmail,ud.getEmail());
        int flag = adminMapper.update(null,wrapper);
        if(flag < 1) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
        }
//        create trigger update_admin
//        after update on admin for each row
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
        if(!removeById(id))
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"用户不存在");
    }

    public void deleteUserByIdNotMe(Integer myId,Integer id) {
        Admin one = getById(id);
        if(one.getId().equals(myId)) {
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"不能删除自己的账号");
        }

        if(!removeById(id)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"用户不存在");
        }
//        create trigger delete_admin
//        after delete on admin for each row
//        begin
//        delete from user where id=old.id;
//        end;
    }
}
