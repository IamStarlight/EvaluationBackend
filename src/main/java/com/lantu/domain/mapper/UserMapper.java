package com.lantu.domain.mapper;

import com.lantu.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    public List<String> getRoleNameByUserID(Integer userId);
    List<User> listStudent();

    List<User> listTeacher();

//    public List<Integer> getUidByUserName(String userName);

}
