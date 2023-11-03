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

    @Autowired
    private CourseMapper courseMapper;

    //管理员查看所有课程
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> getAllCourseInfo(){
        return new ResponseEntity<>(Result.success(courseService.list()), HttpStatus.OK);
    }

    @GetMapping("/info")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> getCourseInfo(@RequestParam @Valid String cid){
        return new ResponseEntity<>(Result.success(courseService.getById(cid)), HttpStatus.OK);
    }

    //更新课程信息
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('1','2')")
    public ResponseEntity<Result> saveOrUpdateCourseInfo(@RequestBody @Valid Course course){
        return new ResponseEntity<>(Result.success(courseService.saveOrUpdateCourseInfo(course)), HttpStatus.OK);
    }

    //管理员删除课程
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> deleteCourse(@RequestBody @Valid String cid){
        return new ResponseEntity<>(Result.success(courseService.deleteCourse(cid)), HttpStatus.OK);
    }

    //管理员根据查询教师所授课程
    // TODO: 2023/11/2 id or tname
    @GetMapping("/teacher/admin")
    @PreAuthorize("hasAnyAuthority('1')")
    public ResponseEntity<Result> getCourseListByTidAdmin(@RequestBody @Valid String message){
        return new ResponseEntity<>(Result.success(courseService.getCourseListByTid(message)), HttpStatus.OK);
    }

    //管理员查询学生所选课程
    // TODO: 2023/11/2 id or name
    @GetMapping("/student/admin")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> getCourseListBySid(@RequestParam @Valid String sid){
        return new ResponseEntity<>(Result.success(courseService.getCourseListBySid(sid)), HttpStatus.OK);
    }

    //教师根据tid查询所授课程
    @GetMapping("/teacher")
    @PreAuthorize("hasAnyAuthority('2')")
    public ResponseEntity<Result> getCourseListByTidTeacher(@CurrentUser User user){
        return new ResponseEntity<>(Result.success(courseService.getCourseListByTid(user.getId())), HttpStatus.OK);
    }

    //学生根据sid查询所选课程
    @GetMapping("/student")
    @PreAuthorize("hasAuthority('3')")
    public ResponseEntity<Result> getCourseListBySid(@CurrentUser User user){
        return new ResponseEntity<>(Result.success(courseService.getCourseListBySid(user.getId())), HttpStatus.OK);
    }

    //查询某课程的选课名单
    @GetMapping("/sc")
    @PreAuthorize("hasAnyAuthority('1','2')")
    public ResponseEntity<Result> getAllSCList(@RequestBody @Valid String cid){
        return new ResponseEntity<>(Result.success(courseService.getAllSCList(cid)), HttpStatus.OK);
    }
}
