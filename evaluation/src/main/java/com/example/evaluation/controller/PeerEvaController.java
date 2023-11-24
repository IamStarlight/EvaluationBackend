package com.example.evaluation.controller;

import com.example.evaluation.annotation.CurrentUser;
import com.example.evaluation.controller.dto.EvaDto;
import com.example.evaluation.controller.dto.OpenPeerDto;
import com.example.evaluation.service.impl.WorkServiceImpl;
import com.example.evaluation.utils.Result;
import com.example.evaluation.entity.User;
import com.example.evaluation.service.impl.PeerServiceImpl;
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
@RequestMapping("/peer")
public class PeerEvaController {

    @Autowired
    private PeerServiceImpl service;

    @Autowired
    private WorkServiceImpl workService;

//--------PostMapping------------------------------------

    //添加一条作业互评评分记录
//    @PostMapping("/addEvaluation")
//    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
//    public ResponseEntity<Result> addEvaluation(@CurrentUser User user,
//                                                @RequestBody @Valid EvaDto d){
//        service.addEvaluation(user.getId(),d);
//        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
//    }

//--------PutMapping------------------------------------
// TODO: 2023-11-23 submit_number -1 
    //老师开启互评 ok
    @PutMapping("/open")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> updateOpenPeer(@RequestBody @Valid OpenPeerDto d){
        workService.updateOpenPeer(d);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    //我评别人 ok
    @PutMapping("/evaluate")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> peerEvaluation(@CurrentUser User user,
                                                 @RequestBody @Valid EvaDto d){
        service.peerEvaluation(user.getId(),d);
        // TODO: 2023-11-24 is_eva=false 
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

//--------GetMapping------------------------------------
    //获取同学A要评价的n份作业
    @GetMapping("/evaluating")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> getEvaluatingStudentVision(@CurrentUser User user,
                                                             @RequestParam @Valid @NotNull(message = "作业id不能为空")
                                                                    Integer wid,
                                                             @RequestParam @Valid @NotNull(message = "课程id不能为空")
                                                                    Integer cid) {
        return new ResponseEntity<>(Result.success(service.getEvaluatingStudentVision(user.getId(), wid, cid)), HttpStatus.OK);
    }

    //获取n个人给同学A作业的评分评语
    @GetMapping("/evaluated")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> getBeEvaluatedStudentVision(@CurrentUser User user,
                                                              @RequestParam @Valid @NotNull(message = "作业id不能为空")
                                                                    Integer wid,
                                                              @RequestParam @Valid @NotNull(message = "课程id不能为空")
                                                                    Integer cid){
        return new ResponseEntity<>(Result.success(service.getBeEvaluatedStudentVision(user.getId(),wid,cid)), HttpStatus.OK);
    }

    @GetMapping("/evaluating/one")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> getEvaluatingOne(@CurrentUser User user,
                                             @RequestParam @Valid @NotNull(message = "被评学生id不能为空")
                                             Integer beEvaSid,
                                             @RequestParam @Valid @NotNull(message = "作业id不能为空")
                                             Integer wid,
                                             @RequestParam @Valid @NotNull(message = "课程id不能为空")
                                             Integer cid){
        return new ResponseEntity<>(Result.success(service.getEvaluatingOne(user.getId(),beEvaSid,wid,cid)), HttpStatus.OK);
    }

    @GetMapping("/evaluated/one")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> getEvaluatedOne(@CurrentUser User user,
                                             @RequestParam @Valid @NotNull(message = "被评学生id不能为空")
                                             Integer beEvaSid,
                                             @RequestParam @Valid @NotNull(message = "作业id不能为空")
                                             Integer wid,
                                             @RequestParam @Valid @NotNull(message = "课程id不能为空")
                                             Integer cid){
        return new ResponseEntity<>(Result.success(service.getEvaluatedOne(user.getId(),beEvaSid,wid,cid)), HttpStatus.OK);
    }

    //获取互评截止日期
    @GetMapping("/deadline")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> getDeadline(@RequestParam @Valid @NotNull(message = "作业id不能为空")
                                             Integer wid,
                                             @RequestParam @Valid @NotNull(message = "课程id不能为空")
                                             Integer cid){
        return new ResponseEntity<>(Result.success(workService.getDeadline(wid,cid)), HttpStatus.OK);
    }

//--------DeleteMapping------------------------------------

}
