package com.lantu.domain.service;

import com.lantu.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface IUserService extends IService<User> {

    Map<String, Object> login(User user);

    User getUserInfo(String token);

    void logout(String token);

    boolean removeUser(User user);

    boolean addUser(User user);

    boolean changePassword(User user,String token);

    boolean changeUserface(User user, String token);

    String returnUploadToken();

    boolean alterUser(User user);

    List<User> getSpecUserInfo(int id);

    boolean changeEmail(User user, String token);

    boolean changeUser(User user);
}
