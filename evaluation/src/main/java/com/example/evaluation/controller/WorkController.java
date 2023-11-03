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

    //查询所有作业
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('1')")
    public ResponseEntity<Result> getAllWorkInfo(){
        return new ResponseEntity<>(Result.success(workService.list()), HttpStatus.OK);
    }

    //根据tid查询作业
//    @GetMapping("/all/teach")
//    @PreAuthorize("hasAnyAuthority('1')")
//    public ResponseEntity<Result> getAllWorkInfoByTid(@RequestParam @Valid String tid){
//        return new ResponseEntity<>(Result.success(workService.getAllWorkInfoByTid(tid)), HttpStatus.OK);
//    }

    //根据sid查询作业
    @GetMapping("/all/student")
    @PreAuthorize("hasAnyAuthority('3')")
    public ResponseEntity<Result> getAllWorkInfoBySid(@RequestParam @Valid String sid){
        return new ResponseEntity<>(Result.success(workService.getAllWorkInfoBySid(sid)), HttpStatus.OK);
    }

    //根据cid查询作业
    @GetMapping("/all/course")
    @PreAuthorize("hasAnyAuthority('1','2','3')")
    public ResponseEntity<Result> getAllWorkInfoByCid(@RequestParam @Valid String cid){
        return new ResponseEntity<>(Result.success(workService.getAllWorkInfoByCid(cid)), HttpStatus.OK);
    }

    //根据wid查询作业 ok
    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('1','2','3')")
    public ResponseEntity<Result> getWorkInfoByWid(@RequestParam @Valid String wid){
        return new ResponseEntity<>(Result.success(workService.getById(wid)), HttpStatus.OK);
    }

    //管理员、教师更改编辑状态 ok
    @PostMapping("/edit/status")
    @PreAuthorize("hasAnyAuthority('1','2')")
    public ResponseEntity<Result> updateEditStatus(@RequestParam @Valid String wid, String status){
        return new ResponseEntity<>(Result.success(workService.updateEditStatus(wid,status)), HttpStatus.OK);
    }

    //管理员、教师更改互评状态 ok
    @PostMapping("/evaluate/status")
    @PreAuthorize("hasAnyAuthority('1','2')")
    public ResponseEntity<Result> updateEvaluateStatus(@RequestParam @Valid String wid,
                                                       @RequestParam @Valid String status){
        return new ResponseEntity<>(Result.success(workService.updateEvaluateStatus(wid,status)), HttpStatus.OK);
    }

    //编辑作业
    // TODO: 2023-11-01 500,400 why?
    @PostMapping("/edit")
    @PreAuthorize("hasAnyAuthority('1','2')")
    public ResponseEntity<Result> updateWorkInfo(@RequestParam @Valid String wid,
                                                 @RequestBody @Valid WorkDto wd){
        return new ResponseEntity<>(Result.success(workService.updateWorkInfo(wid,wd)), HttpStatus.OK);
    }

    //发布作业
    // TODO: 2023-11-02 400 why?
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('1','2')")
    public ResponseEntity<Result> setNewWork(@RequestBody @Valid WorkDto wd){
        return new ResponseEntity<>(Result.success(workService.setNewWork(wd)), HttpStatus.OK);
    }

    //交作业
    // TODO: 2023-11-01 500 saveorupdate
    @PostMapping("/submit")
    @PreAuthorize("hasAnyAuthority('3')")
    public ResponseEntity<Result> submitWork(@RequestBody @Valid SubmitDto submitDto){
        return new ResponseEntity<>(Result.success(submitService.submitWork(submitDto)), HttpStatus.OK);
    }

    //老师批改作业
    @PostMapping("/teacher/evaluation")
    @PreAuthorize("hasAnyAuthority('3')")
    public ResponseEntity<Result> teacherEvaluation(@RequestBody @Valid TeacherEvaDto td){
        return new ResponseEntity<>(Result.success(submitService.teacherEvaluation(td)), HttpStatus.OK);
    }

    // TODO: 2023-10-31 statistics 

    // TODO: 2023-11-03 作业状态更新通知

    // TODO: 2023-11-02 sendEmail 


}
