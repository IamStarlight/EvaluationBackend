package com.lantu.domain.controller;

import com.alibaba.fastjson2.JSON;
import com.lantu.common.vo.Result;
import com.lantu.domain.entity.Course;
import com.lantu.domain.entity.SC;
import com.lantu.domain.entity.User;
import com.lantu.domain.mapper.SCMapper;
import com.lantu.domain.mapper.UserMapper;
import com.lantu.domain.service.ISCService;
import com.lantu.domain.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/domain/sc")
public class SCController {

    private ISCService scService;
    private SCMapper scMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    //学生查看自己所选的课程
    @GetMapping("/studentForCourse")
    public Result<List<Course>> getMyCourse(@RequestHeader("Token") String token){
        Object object = redisTemplate.opsForValue().get(token);
        if(object != null){
            User user1 = JSON.parseObject(JSON.toJSONString(object),User.class);

            List<Course> data= scMapper.getMyCourse(user1.getId());
            if (data != null){
                return Result.success(data);
            }
        }

        return Result.error(20002,"用户登录信息失效");
    }

    //老师查看自己所交的课程有谁选择
    @PostMapping("/teacherForCourse")
    public Result<List<User>> getTeacherCourse(@RequestBody Course course,@RequestHeader("Token") String token){
        Object object = redisTemplate.opsForValue().get(token);
        if(object != null){
            User user1 = JSON.parseObject(JSON.toJSONString(object),User.class);

            List<User> data= scMapper.getTeacherCourse(user1.getId(),course.getCid());
            if (data != null){
                return Result.success(data);
            }
        }

        return Result.error(20002,"用户登录信息失效");
    }


    //管理员查看课程有谁选择
    @PostMapping("/allCourseStudent")
    public  Result<List<User>> getAllCourseStudent(@RequestBody Course course){

        List<User> data= scMapper.getAllCourseStudent(course.getCid());
        if (data != null){
            return Result.success(data);
        }

        return Result.error(20002,"用户登录信息失效");
    }






}
