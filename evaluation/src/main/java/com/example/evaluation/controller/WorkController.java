package com.example.evaluation.controller;

import com.example.evaluation.controller.dto.SubmitDto;
import com.example.evaluation.controller.dto.TeacherEvaDto;
import com.example.evaluation.controller.dto.WorkDto;
import com.example.evaluation.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.evaluation.service.impl.SubmitServiceImpl;
import com.example.evaluation.service.impl.WorkServiceImpl;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private WorkServiceImpl workService;

    @Autowired
    private SubmitServiceImpl submitService;

    //根据sid查询作业
    @GetMapping("/student")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> getAllWorkInfoBySid(@RequestParam @Valid String sid){
        return new ResponseEntity<>(Result.success(workService.getAllWorkInfoBySid(sid)), HttpStatus.OK);
    }

    //查询对应课程的全部作业 ok
    @GetMapping("/bycourse")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> getAllWorkInfoByCid(@RequestParam @Valid String cid){
        return new ResponseEntity<>(Result.success(workService.getAllWorkInfoByCid(cid)), HttpStatus.OK);
    }

    //教师获得作业信息 标题 截止日期 状态 提交人数 课堂人数
    @GetMapping("/teacher")
    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    public ResponseEntity<Result> getWorkInfoById(@RequestParam @Valid Integer wid,
                                                   @RequestParam @Valid Integer cid){
        return new ResponseEntity<>(Result.success(workService.getWorkInfoById(wid,cid)), HttpStatus.OK);
    }

//    //根据wid查询作业
//    @GetMapping("/info")
//    @PreAuthorize("hasAnyAuthority('ROLE_AMDIN','ROLE_TEACHER','ROLE_STUDENT')")
//    public ResponseEntity<Result> getWorkInfoByWid(@RequestParam @Valid String wid){
//        return new ResponseEntity<>(Result.success(workService.getById(wid)), HttpStatus.OK);
//    }

    //管理员、教师更改编辑状态
//    @PostMapping("/edit/status")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
//    public ResponseEntity<Result> updateEditStatus(@RequestParam @Valid String wid, String status){
//        return new ResponseEntity<>(Result.success(workService.updateEditStatus(wid,status)), HttpStatus.OK);
//    }
//
//    //管理员、教师更改互评状态
//    @PostMapping("/evaluate/status")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
//    public ResponseEntity<Result> updateEvaluateStatus(@RequestParam @Valid String wid,
//                                                       @RequestParam @Valid String status){
//        return new ResponseEntity<>(Result.success(workService.updateEvaluateStatus(wid,status)), HttpStatus.OK);
//    }

    //编辑作业
    // TODO: 2023-11-01 500,400 why?
    @PostMapping("/edit")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> updateWorkInfo(@RequestParam @Valid String wid,
                                                 @RequestBody @Valid WorkDto wd){
        return new ResponseEntity<>(Result.success(workService.updateWorkInfo(wid,wd)), HttpStatus.OK);
    }

    //发布作业
    // TODO: 2023-11-02 400 why?
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> setNewWork(@RequestBody @Valid WorkDto wd){
        return new ResponseEntity<>(Result.success(workService.setNewWork(wd)), HttpStatus.OK);
    }

    //交作业
    @PostMapping("/submit")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> submitWork(@RequestBody @Valid SubmitDto submitDto){
        return new ResponseEntity<>(Result.success(submitService.submitWork(submitDto)), HttpStatus.OK);
    }

    // TODO: 2023-11-06 作业提交名单
    @PostMapping("/submitlist")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> getSubmitList(@RequestParam @Valid Integer wid,
                                                @RequestParam @Valid Integer cid){
        return new ResponseEntity<>(Result.success(submitService.getSubmitList(wid,cid)), HttpStatus.OK);
    }

    //老师批改作业
    @PostMapping("/evaluate")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> teacherEvaluation(@RequestBody @Valid TeacherEvaDto td){
        return new ResponseEntity<>(Result.success(submitService.teacherEvaluation(td)), HttpStatus.OK);
    }

    // TODO: 2023-10-31 statistics 

    // TODO: 2023-11-03 作业状态更新通知

    // TODO: 2023-11-02 sendEmail 


}
