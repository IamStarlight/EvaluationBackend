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


    // TODO: 2023/11/1 getSCStudent 1,2
    @GetMapping("/teacherForSC")
    @PreAuthorize("hasAuthority('1','2')")
    public ResponseEntity<Result> getSCStudent(String tid, String cid){
        return new ResponseEntity<>(Result.success(courseService.getSCStudent(tid,  cid)), HttpStatus.OK);
    }

    // TODO: 2023/11/1  getAllSCStudent 1


    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> deleteCourse(String cid){
        return new ResponseEntity<>(Result.success(courseService.deleteCourse(cid)), HttpStatus.OK);
    }

}
