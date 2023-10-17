package service;

import com.baomidou.mybatisplus.extension.service.IService;
import domain.User;

public interface UserEmailService extends IService<User> {

    String sendEmailCode(String email);
}