package com.example.evaluation.controller;

import com.example.evaluation.domain.Course;
import com.example.evaluation.domain.Result;
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

    @GetMapping("/info")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> getCourseInfo(){
        return new ResponseEntity<>(Result.success(courseService.getAllCourseInfo()), HttpStatus.OK);
    }

    @GetMapping("/teacher")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> getCourseInfoByTid(String tid){
        return new ResponseEntity<>(Result.success(courseService.getCourseInfoByTid(tid)), HttpStatus.OK);
    }

    // TODO: 2023-11-01 getCourseInfoBySid 3 多表联查
//    @GetMapping("/teacher")
//    @PreAuthorize("hasAuthority('1')")
//    public ResponseEntity<Result> getCourseInfoBySid(String sid){
//        return new ResponseEntity<>(Result.success(courseService.getCourseInfoBySid(sid)), HttpStatus.OK);
//    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> saveOrUpdateCourseInfo(@RequestBody @Valid Course course){
        return new ResponseEntity<>(Result.success(courseService.saveOrUpdateCourseInfo(course)), HttpStatus.OK);
    }

}
