package service;

import controller.dto.LoginDto;
import controller.dto.RegisterDto;
import controller.dto.UpdateDto;
import domain.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    HashMap<String,String> login(LoginDto loginDto);

    User register(RegisterDto registerDto);

    List<User> getAllUserInfo();

    User getUserByID(String uid);

    boolean deleteUserById(String uid);

    boolean updateUserInfo(UpdateDto ud);

    boolean updateUserName(String id,String name);

    boolean updateUserPwd(String id,String oldpwd, String newpwd);

    boolean updateUserPer(String id,String per);

    boolean updateUserEmail(String id,String email);

    String logout();
}
