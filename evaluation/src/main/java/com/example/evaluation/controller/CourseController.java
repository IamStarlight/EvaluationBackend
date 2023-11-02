package com.example.evaluation.controller;

import com.example.evaluation.domain.Course;
import com.example.evaluation.domain.Result;
import com.example.evaluation.mapper.CourseMapper;
import org.apache.ibatis.type.NStringTypeHandler;
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

    @GetMapping("/info")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> getCourseInfo(){
        return new ResponseEntity<>(Result.success(courseService.getAllCourseInfo()), HttpStatus.OK);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('1','2')")
    public ResponseEntity<Result> saveOrUpdateCourseInfo(@RequestBody @Valid Course course){
        return new ResponseEntity<>(Result.success(courseService.saveOrUpdateCourseInfo(course)), HttpStatus.OK);
    }

    // TODO: 2023/11/1 deleteCourse 1
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> deleteCourse(@RequestBody @Valid String cid){
        return new ResponseEntity<>(Result.success(courseService.deleteCourse(cid)), HttpStatus.OK);
    }

//    @GetMapping("/teacher")
//    @PreAuthorize("hasAuthority('1')")
//    public ResponseEntity<Result> getCourseInfoByTname(String tname){
//        return new ResponseEntity<>(Result.success(courseService.getCourseInfoByTid(tname)), HttpStatus.OK);
//    }

    @GetMapping("/teacherCourse")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> getCourseInfoByTname(@RequestBody @Valid String tname){
        return new ResponseEntity<>(Result.success(courseMapper.getCourseInfoByTname(tname)), HttpStatus.OK);
    }


    @GetMapping("/studentCourse")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> getCourseInfoBySname(@RequestBody @Valid String sname){
        return new ResponseEntity<>(Result.success(courseMapper.getCourseInfoBySname(sname)), HttpStatus.OK);
    }


    @GetMapping("/allSCList")
    @PreAuthorize("hasAuthority('1')")
    public ResponseEntity<Result> getAllSCList(@RequestBody @Valid String cid){
        return new ResponseEntity<>(Result.success(courseMapper.getAllSCList(cid)), HttpStatus.OK);
    }


    // TODO: 2023/11/1 getCourseListByTid 2
    @GetMapping("/courseListByTid")
    @PreAuthorize("hasAuthority('2')")
    //使用token
    public ResponseEntity<Result> getCourseListByTid(@RequestBody @Valid String tid){
        return new ResponseEntity<>(Result.success(courseService .getCourseListByTid(tid)), HttpStatus.OK);
    }

    // TODO: 2023/11/1 getSCListByCid 2
    @GetMapping("/scListByCid")
    @PreAuthorize("hasAuthority('2')")
    public ResponseEntity<Result> getSCListByCid(@RequestBody @Valid String cid){
        return new ResponseEntity<>(Result.success(courseService .getSCListByCid(cid)), HttpStatus.OK);
    }

    // TODO: 2023/11/1 getCourseListBySid 3
    @GetMapping("/courseListBySid")
    @PreAuthorize("hasAuthority('3')")
    //使用token
    public ResponseEntity<Result> getCourseListBySid(@RequestBody @Valid String sid){
        return new ResponseEntity<>(Result.success(courseService .getCourseListBySid(sid)), HttpStatus.OK);
    }


}
