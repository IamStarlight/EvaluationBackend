package com.example.evaluation.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.entity.User;
import com.example.evaluation.mapper.UserMapper;
import com.example.evaluation.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EmailServiceImpl
        extends ServiceImpl<UserMapper, User>
        implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    // TODO: 2023-11-03 新发布作业通知
    @Override
    public void sendNewWorkNotice(String cname,String title, List<Map<String,String>> list) {
//        for (Map student: list){
//            SimpleMailMessage msg = new SimpleMailMessage();
//            msg.setFrom(from);
//            msg.setTo(student.getEmail());
//            msg.setSentDate(new Date());
//            msg.setSubject(cname + "发布了新的作业" + title);
//            msg.setText(student.getName() + "你好：");
//            // TODO: 2023-11-03 邮件发送模板
//            javaMailSender.send(msg);
//        }
    }
    // TODO: 2023-11-03 作业未交提醒（ddl当天） sendNotSubmittedReminder
    // TODO: 2023-11-03 作业互评提醒 sendPearEvaluationReminder
    // TODO: 2023-11-03 成绩发布提醒 sendGradeNotice
}

