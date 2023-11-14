package com.example.evaluation.controller;

import com.example.evaluation.annotation.CurrentUser;
import com.example.evaluation.controller.dto.EvaDto;
import com.example.evaluation.entity.Result;
import com.example.evaluation.entity.User;
import com.example.evaluation.service.impl.PeerServiceImpl;
import com.example.evaluation.service.impl.SubmitServiceImpl;
import com.example.evaluation.service.impl.WorkServiceImpl;
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

//--------PostMapping------------------------------------
//--------PutMapping------------------------------------

    //同学互评,评分评语
    // TODO: 2023-11-14 评一个，已评价人数+1 
    @PutMapping("/evaluate")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> peerEvaluation(@CurrentUser User user,
                                                 @RequestBody @Valid EvaDto d){
        service.peerEvaluation(user.getId(),d);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

//--------GetMapping------------------------------------
    // TODO: 2023-11-14 获取要评价的作业份数和作业们 （评分的学生）

    // TODO: 2023-11-14  获取同学A的作业的评价名单，分数，评论（教师）

    // TODO: 2023-11-14 获取同学A的作业的互评分数们，评论们（被评的学生） 
//--------DeleteMapping------------------------------------

}
