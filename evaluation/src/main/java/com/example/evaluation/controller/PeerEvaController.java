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

    //添加一条作业互评评分记录
    // TODO: 2023-11-14
//    @PostMapping("/addEvaluation")
//    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
//    public ResponseEntity<Result> addEvaluation(@CurrentUser User user,
//                                                @RequestBody @Valid EvaDto d){
//        service.addEvaluation(user.getId(),d);
//        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
//    }

//--------PutMapping------------------------------------

    //同学互评,评分评语
    // TODO: 2023-11-14 评一个，已评价人数+1 
    @PutMapping("/updateEvaluation")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> peerEvaluation(@CurrentUser User user,
                                                 @RequestBody @Valid EvaDto d){
        service.peerEvaluation(user.getId(),d);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    //教师评分评语
    // TODO: 2023-11-17
    @PutMapping("/teacherEvaluation")
    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    public ResponseEntity<Result> teaEvaluation(@CurrentUser User user,
                                                 @RequestBody @Valid EvaDto d){
        service.teaEvaluation(user.getId(),d);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

//--------GetMapping------------------------------------

    // TODO: 2023-11-14 获取要评价的作业份数和作业们 （评分的学生）
    @GetMapping("/Allwork")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> selectAllWork(@CurrentUser User user,
                                                @RequestBody @Valid EvaDto d){
        service.selectAllWork(user.getId(), d.getCid());
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    // TODO: 2023-11-18 获取要评价的作业份数和作业们 （教师）
    @GetMapping("/AllworkForTeacher")
    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    public ResponseEntity<Result> selectTeaAllWork(@CurrentUser User user,
                                                @RequestBody @Valid EvaDto d){
        service.selectTeaAllWork(user.getId(), d.getCid());
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    // TODO: 2023-11-14  获取同学A的作业的评价名单，分数，评论（教师）
    @GetMapping("/EvaluationList")
    @PreAuthorize("hasAnyAuthority('POLE_TEACHER')")
    public ResponseEntity<Result> selectForTeacher(@RequestBody @Valid @NotNull(message = "评价学生id不能为空")
                                                   Integer evaSid,
                                                   @RequestBody @Valid @NotNull(message = "作业id不能为空")
                                                   Integer wid){
        service.selectForTeacher(evaSid, wid);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }


    // TODO: 2023-11-14 获取同学A的作业的互评分数们，评论们（被评的学生）
    @GetMapping("/EvaluationForOneWork")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> selectForStudent(@RequestBody @Valid @NotNull(message = "被评学生id不能为空")
                                                   Integer sid,
                                                   @RequestBody @Valid @NotNull(message = "课程id不能为空")
                                                   Integer cid,
                                                   @RequestBody @Valid @NotNull(message = "作业id不能为空")
                                                   Integer wid){
        service.selectForStudent(sid, cid, wid);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    // TODO: 2023-11-16 获取其中的一份需要互评的作业
    @GetMapping("/OneWork")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> selectForOneWork(@RequestBody @Valid @NotNull(message = "被评学生id不能为空")
                                                   Integer sid,
                                                   @RequestBody @Valid @NotNull(message = "课程id不能为空")
                                                   Integer cid,
                                                   @RequestBody @Valid @NotNull(message = "作业id不能为空")
                                                   Integer wid) {
        service.selectOneWork(sid, cid, wid);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    // TODO: 2023-11-18 教师获取其中的一份需要互评的作业
    @GetMapping("/OneWorkForTeacher")
    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    public ResponseEntity<Result> selectForOneWorkTea(@RequestBody @Valid @NotNull(message = "被评学生id不能为空")
                                                   Integer sid,
                                                   @RequestBody @Valid @NotNull(message = "课程id不能为空")
                                                   Integer cid,
                                                   @RequestBody @Valid @NotNull(message = "作业id不能为空")
                                                   Integer wid) {
        service.selectOneWorkForTea(sid, cid, wid);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

//--------DeleteMapping------------------------------------

    //貌似没有需要删除的，只需查看、修改即可
}
