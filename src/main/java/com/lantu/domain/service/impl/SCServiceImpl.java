package com.lantu.domain.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lantu.domain.entity.Course;
import com.lantu.domain.entity.SC;
import com.lantu.domain.entity.User;
import com.lantu.domain.mapper.SCMapper;
import com.lantu.domain.service.ISCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SCServiceImpl extends ServiceImpl<SCMapper, SC> implements ISCService {

}
