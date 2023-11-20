package com.example.evaluation.controller;

import com.example.evaluation.annotation.CurrentUser;
import com.example.evaluation.controller.dto.EvaDto;
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

@RestController
@Validated
@RequestMapping("/peer")
public class PeerEvaController {

    @Autowired
    private PeerServiceImpl service;

//--------PostMapping------------------------------------
//--------PutMapping------------------------------------

    // TODO: 2023-11-20  //我评别人 现在没有生成名单！！！
    // TODO: 2023-11-14 评一个，已评价人数+1
    @PutMapping("/evaluate")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> peerEvaluation(@CurrentUser User user,
                                                 @RequestBody @Valid EvaDto d){
        service.peerEvaluation(user.getId(),d);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

//--------GetMapping------------------------------------
    // TODO: 2023-11-20  //获取要评价的作业份数和作业们 （评分的学生）
    @GetMapping("/evaluating")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> getEvaluatingStudentVision(@CurrentUser User user){
        return new ResponseEntity<>(Result.success(service.getEvaluatingStudentVision(user.getId())), HttpStatus.OK);
    }

    // TODO: 2023-11-20  //获取同学A的作业的互评分数们，评论们（被评的学生）
    @GetMapping("/beevaluated")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> getBeEvaluatedStudentVision(@CurrentUser User user){
        return new ResponseEntity<>(Result.success(service.getBeEvaluatedStudentVision(user.getId())), HttpStatus.OK);
    }

//--------DeleteMapping------------------------------------

}
