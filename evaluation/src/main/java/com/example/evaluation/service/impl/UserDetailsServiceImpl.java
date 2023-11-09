package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.entity.User;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.UserMapper;
import com.example.evaluation.utils.LoginUser;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl extends ServiceImpl<UserMapper, User> implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String id) {
        User user = getById(id);
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"用户名或密码错误");
        }
//        System.out.println("!!!!!!UserDetailServiceImpl user:"+user.getId()+", "+user.getName());

        //根据用户查询权限信息 添加到LoginUser中
        List<String> permissionKeyList =
                Collections.singletonList(
                        String.valueOf(userService.getPermsById(user.getId())));
//        System.out.println("!!!!!!authorities in UserDetail: "+userService.getPermsById(user.getId()));

        LoginUser loginUser = new LoginUser(user,permissionKeyList);
//        System.out.println("!!!!!!UserDetailServiceImpl LoginUser: "+loginUser.getUser().getId()+", "+loginUser.getUser().getName());

        //封装成UserDetails对象返回
//        return new LoginUser(user,permissionKeyList);
        return loginUser;
    }
}

