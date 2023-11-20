package com.example.evaluation.controller;

import com.example.evaluation.utils.Result;
import com.example.evaluation.service.impl.ScServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Validated
@RequestMapping("/sc")
public class ScController {

    @Autowired
    private ScServiceImpl service;

    // TODO: 2023-11-20  // 添加选课名单，一次加一个 ok
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> addScStu(@RequestParam @Valid Integer sid,
                                           @RequestParam @Valid Integer cid){
        service.addScStu(sid,cid);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    // TODO: 2023-11-20  // 根据学号搜索选课名单中的一个学生 ok
    @GetMapping("/student")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> getOneSCStudent(@RequestParam @Valid @NotNull(message = "学号不能为空")
                                                   Integer sid,
                                               @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                    Integer cid){
        return new ResponseEntity<>(Result.success(service.getOneSCStudent(sid,cid)), HttpStatus.OK);
    }

    //查询某课程的选课名单 ok
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> getAllSCList(@RequestParam @Valid @NotNull(message = "课程号不能为空")
                                               Integer cid){
        return new ResponseEntity<>(Result.success(service.getAllSCList(cid)), HttpStatus.OK);
    }

    // 删除选课学生
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> deleteScStu(@RequestParam @Valid Integer sid,
                                              @RequestParam @Valid Integer cid){
        service.deleteScStu(sid,cid);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

}
