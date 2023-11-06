package com.example.evaluation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.evaluation.entity.User;

import java.util.List;
import java.util.Map;

public interface EmailService extends IService<User> {

    void sendNewWorkNotice(String cname,String title, List<Map<String,String>> list);
}