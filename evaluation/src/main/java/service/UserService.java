package service;

import controller.dto.LoginDto;
import controller.dto.RegisterDto;
import controller.dto.UpdateDto;
import domain.User;

import java.util.HashMap;

public interface UserService {

//    HashMap<String,String> login(LoginDto loginDto);

    User register(RegisterDto registerDto);

    String getPermsByID(String id);

    boolean deleteUserById(String uid);

    boolean updateUserInfo(UpdateDto ud);

    boolean updateUserName(String id,String name);

    boolean updateUserPwd(String id,String oldpwd, String newpwd);

    boolean updateUserPer(String id,String per);

    boolean updateUserEmail(String id,String email);

//    String logout();
}
