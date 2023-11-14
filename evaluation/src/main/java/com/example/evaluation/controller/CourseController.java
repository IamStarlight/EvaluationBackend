package com.example.evaluation.controller;

import com.example.evaluation.annotation.CurrentUser;
import com.example.evaluation.controller.dto.CourseDto;
import com.example.evaluation.entity.Course;
import com.example.evaluation.entity.Result;
import com.example.evaluation.entity.User;
import com.example.evaluation.service.impl.ScServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.evaluation.service.impl.CourseServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Validated
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseServiceImpl service;

    @Autowired
    private ScServiceImpl scService;

//--------PostMapping------------------------------------

    //管理员增加课程 ok
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> addNewCourse(@RequestBody @Valid CourseDto dto){
        service.addNewCourse(dto);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

//--------PutMapping------------------------------------

    //教师修改课程简介 ok
    @PutMapping("/modify")
    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    public ResponseEntity<Result> updateCourseInfo(@RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                   Integer cid,
                                                   @RequestParam @Valid @NotNull(message = "课程简介不能为空")
                                                   String content){
        service.updateCourseInfo(cid,content);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    //管理员更新课程信息 ok
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> updateCourseInfo(@RequestBody @Valid Course course){
        service.updateCourseInfo(course);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

//--------GetMapping------------------------------------

    //管理员查看所有课程 ok
    // TODO: 2023-11-09 tname
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> getAllCourseInfo(){
        return new ResponseEntity<>(Result.success(service.getAllCourseInfo()), HttpStatus.OK);
    }

    //根据课程号查询课程 ok
    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> getCourseInfo(@RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                Integer cid){
        return new ResponseEntity<>(Result.success(service.getCourseInfo(cid)), HttpStatus.OK);
    }

    //管理员根据工号查询教师所授课程 ok
    // TODO: 2023/11/2 id or tname
    @GetMapping("/teacher/admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> getCourseListByTidAdmin(@RequestParam @Valid @NotNull(message = "工号不能为空")
                                                          Integer tid){
        return new ResponseEntity<>(Result.success(service.getCourseListByTid(tid)), HttpStatus.OK);
    }

    //管理员查询学生所选课程 ok
    // TODO: 2023/11/2 id or name
    @GetMapping("/student/admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> getCourseListBySid(@RequestParam @Valid @NotNull(message = "学号不能为空")
                                                     Integer sid){
        return new ResponseEntity<>(Result.success(service.getCourseListBySid(sid)), HttpStatus.OK);
    }

    //教师查询自己所授课程 ok
    @GetMapping("/teacher")
    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    public ResponseEntity<Result> getCourseListByTidTeacher(@CurrentUser User user){
        return new ResponseEntity<>(Result.success(service.getCourseListByTid(user.getId())), HttpStatus.OK);
    }

    //学生查询自己所选课程 ok
    @GetMapping("/student")
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> getCourseListBySid(@CurrentUser User user){
        return new ResponseEntity<>(Result.success(service.getCourseListBySid(user.getId())), HttpStatus.OK);
    }

//--------DeleteMapping------------------------------------

    //管理员删除课程 ok
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> deleteCourse(@RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                   Integer cid){
        service.deleteCourse(cid);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    // TODO: 2023-11-09 转时间
    // TODO: 2023-11-09 wid自增
    // TODO: 2023-11-09 作业状态

}
