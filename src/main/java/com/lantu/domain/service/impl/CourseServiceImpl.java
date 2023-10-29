package com.lantu.domain.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lantu.domain.entity.Course;
import com.lantu.domain.entity.User;
import com.lantu.domain.mapper.CourseMapper;
import com.lantu.domain.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean addCourse(Course course) {
        int a = this.baseMapper.insert(course);
        if(a==1){
            return true;
        }
        return false;

    }

    @Override
    public boolean removeCourse(Course course) {
        Map<String,Object> de= new HashMap<>();
        de.put("cid",course.getCid());
        if(this.baseMapper.deleteByMap(de)!=0){
            return true;
        }
        return false;
    }

    @Override
    public boolean changeCourse(Course course) {
        this.baseMapper.updateById(course);
        return true;
    }

    @Override
    public List<Course> getMyCourse(String token) {
        Object object = redisTemplate.opsForValue().get(token);
        Map<String,Object> re = new HashMap<>();


        if(object != null){
            User user1 = JSON.parseObject(JSON.toJSONString(object),User.class);
            re.put("tid",user1.getId());
//            User user = this.baseMapper.selectById(user1);
            List<Course> courses = this.baseMapper.selectByMap(re);
            return courses;

        }
        return null;
    }

}
