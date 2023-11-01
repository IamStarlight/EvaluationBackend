package com.example.evaluation.controller;

import com.example.evaluation.controller.dto.SubmitDto;
import com.example.evaluation.controller.dto.TeacherEvaDto;
import com.example.evaluation.controller.dto.WorkDto;
import com.example.evaluation.domain.Result;
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

    // TODO: 2023-11-01 getAllWorkInfo  1

    // TODO: 2023-11-01 getAllWorkInfoByTid 2

    // TODO: 2023-11-01 getAllWorkInfoBySid 3

    // TODO: 2023-11-01 getAllWorkInfoByCid 1,2,3 

    //根据wid查询作业 ok
    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('1','2','3')")
    public ResponseEntity<Result> getWorkInfoByWid(@RequestParam @Valid String wid){
        return new ResponseEntity<>(Result.success(workService.getWorkInfoByWid(wid)), HttpStatus.OK);
    }

    //更改编辑状态 ok
    @PostMapping("/edit/status")
    @PreAuthorize("hasAnyAuthority('1','2')")
    public ResponseEntity<Result> updateEditStatus(@RequestParam @Valid String wid, String status){
        return new ResponseEntity<>(Result.success(workService.updateEditStatus(wid,status)), HttpStatus.OK);
    }

    //更改互评状态 ok
    @PostMapping("/evaluate/status")
    @PreAuthorize("hasAnyAuthority('1','2')")
    public ResponseEntity<Result> updateEvaluateStatus(@RequestParam @Valid String wid,
                                                       @RequestParam @Valid String status){
        return new ResponseEntity<>(Result.success(workService.updateEvaluateStatus(wid,status)), HttpStatus.OK);
    }

    // TODO: 2023-11-01 500 
    @PostMapping("/edit")
    @PreAuthorize("hasAnyAuthority('1','2')")
    public ResponseEntity<Result> saveOrUpdateWorkInfo(@RequestBody @Valid WorkDto workDto){
        return new ResponseEntity<>(Result.success(workService.saveOrUpdateWorkInfo(workDto)), HttpStatus.OK);
    }

    // TODO: 2023-11-01 500 
    @PostMapping("/submit")
    @PreAuthorize("hasAnyAuthority('3')")
    public ResponseEntity<Result> submitWork(@RequestBody @Valid SubmitDto submitDto){
        return new ResponseEntity<>(Result.success(submitService.submitWork(submitDto)), HttpStatus.OK);
    }

    // TODO: 2023-10-31 uploadAttachments 

    // TODO: 2023-10-31 downloadAttachments 

    @PostMapping("/teacher/evaluation")
    @PreAuthorize("hasAnyAuthority('3')")
    public ResponseEntity<Result> teacherEvaluation(@RequestBody @Valid TeacherEvaDto td){
        return new ResponseEntity<>(Result.success(submitService.teacherEvaluation(td)), HttpStatus.OK);
    }

    // TODO: 2023-10-31 statistics 



}
