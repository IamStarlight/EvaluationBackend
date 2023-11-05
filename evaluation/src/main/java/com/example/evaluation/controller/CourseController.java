package com.example.evaluation.controller;

import com.example.evaluation.annotation.CurrentUser;
import com.example.evaluation.entity.Course;
import com.example.evaluation.entity.Result;
import com.example.evaluation.entity.User;
import com.example.evaluation.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.evaluation.service.impl.CourseServiceImpl;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    //管理员查看所有课程
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> getAllCourseInfo(){
        return new ResponseEntity<>(Result.success(courseService.list()), HttpStatus.OK);
    }

    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> getCourseInfo(@RequestParam @Valid String cid){
        return new ResponseEntity<>(Result.success(courseService.getById(cid)), HttpStatus.OK);
    }

    //更新课程信息
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> saveOrUpdateCourseInfo(@RequestBody @Valid Course course){
        return new ResponseEntity<>(Result.success(courseService.saveOrUpdateCourseInfo(course)), HttpStatus.OK);
    }

    //管理员删除课程
    @PostMapping("/delete")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> deleteCourse(@RequestBody @Valid Integer cid){
        return new ResponseEntity<>(Result.success(courseService.deleteCourse(cid)), HttpStatus.OK);
    }

    //管理员根据查询教师所授课程
    // TODO: 2023/11/2 id or tname
    @GetMapping("/teacher/admin")
    @PreAuthorize("hasAnyAuthority('ROLE_AMDIN')")
    public ResponseEntity<Result> getCourseListByTidAdmin(@RequestBody @Valid Integer message){
        return new ResponseEntity<>(Result.success(courseService.getCourseListByTid(message)), HttpStatus.OK);
    }

    //管理员查询学生所选课程
    // TODO: 2023/11/2 id or name
    @GetMapping("/student/admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> getCourseListBySid(@RequestParam @Valid Integer sid){
        return new ResponseEntity<>(Result.success(courseService.getCourseListBySid(sid)), HttpStatus.OK);
    }

    //教师根据tid查询所授课程
    // TODO: 2023-11-05 学生人数
    @GetMapping("/teacher")
    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    public ResponseEntity<Result> getCourseListByTidTeacher(@CurrentUser User user){
        return new ResponseEntity<>(Result.success(courseService.getCourseListByTid(user.getId())), HttpStatus.OK);
    }

    //学生根据sid查询所选课程
    @GetMapping("/student")
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> getCourseListBySid(@CurrentUser User user){
        return new ResponseEntity<>(Result.success(courseService.getCourseListBySid(user.getId())), HttpStatus.OK);
    }

    //查询某课程的选课名单
    @GetMapping("/sc")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> getAllSCList(@RequestBody @Valid Integer cid){
        return new ResponseEntity<>(Result.success(courseService.getAllSCList(cid)), HttpStatus.OK);
    }
}
