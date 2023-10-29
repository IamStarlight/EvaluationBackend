package com.lantu.domain.controller;


import com.lantu.common.vo.Result;
import com.lantu.domain.entity.Course;
import com.lantu.domain.mapper.CourseMapper;
import com.lantu.domain.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/domain/course")
public class CourseController {

    private ICourseService courseService;
    private CourseMapper courseMapper;

    //管理员查看所有课程
    @GetMapping("/allCourse")
    public Result<List<Course>> getAllCourse(){
        List<Course>list = courseService.list();
        return Result.success(list);
    }
    //管理员添加课程
    @PostMapping("/addCourse")
    public  Result<Object> addCourse(@RequestBody Course course){
        boolean a = courseService.addCourse(course);
        if(a){
            return Result.success();
        }
        return Result.error(20004,"插入失败");
    }

    //管理员删除课程
    @PostMapping("/removeCourse")
    public  Result<Object> removeCourse(@RequestBody Course course){
        boolean a = courseService.removeCourse(course);
        if(a){
            return Result.success();
        }
        return Result.error(20005,"删除失败");
    }

    //管理员修改课程
    @PostMapping("/changeCourse")
    public  Result<Object> changeCourse(@RequestBody Course course){
        boolean a = courseService.changeCourse(course);
        if(a){
            return Result.success();
        }
        return Result.error(20005,"修改失败");
    }

    //老师查看所教课程
    @GetMapping("/teacherForCourse")
    public Result<List<Course>> getMyCourse(@RequestHeader("Token") String token){

        List<Course> data= courseService.getMyCourse(token);
        if (data != null){
            return Result.success(data);
        }
        return Result.error(20002,"用户登录信息失效");
    }

}
