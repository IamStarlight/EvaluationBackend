package com.example.evaluation.controller;

import com.example.evaluation.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.evaluation.service.impl.CourseServiceImpl;

@RestController
@Validated
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @GetMapping("/info")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> getCourseInfo(){
        return new ResponseEntity<>(Result.success(courseService.getAllCourseInfo()), HttpStatus.OK);
    }

    // TODO: 2023-11-01 getCourseInfoByTid 2

    // TODO: 2023-11-01 getCourseInfoBySid 3

    // TODO: 2023-11-01 saveOrUpdateCourseInfo 1

    // TODO: 2023-11-01 deleteCourse 1
    //asd

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
