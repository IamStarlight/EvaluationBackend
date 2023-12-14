package com.example.evaluation.controller;

import com.example.evaluation.annotation.CurrentUser;
import com.example.evaluation.entity.User;
import com.example.evaluation.service.impl.WorkServiceImpl;
import com.example.evaluation.utils.Result;
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
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private WorkServiceImpl workService;

    @GetMapping("/layers")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> get_all_score_layers(@RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                   Integer cid){
        return new ResponseEntity<>(Result.success(workService.get_all_score_layers(cid)), HttpStatus.OK);
    }

    @GetMapping("/scores")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> get_score_layers(@RequestParam @Valid @NotNull(message = "作业号不能为空")
                                            Integer wid,
                                            @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                            Integer cid){
        return new ResponseEntity<>(Result.success(workService.get_score_layers(wid,cid)), HttpStatus.OK);
    }

    @GetMapping("/nighties")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> get90_100(@RequestParam @Valid @NotNull(message = "作业号不能为空")
                                               Integer wid,
                                           @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                               Integer cid){
        return new ResponseEntity<>(Result.success(workService.get90_100(wid,cid)), HttpStatus.OK);
    }

    @GetMapping("/eighties")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> get80_90(@RequestParam @Valid @NotNull(message = "作业号不能为空")
                                           Integer wid,
                                           @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                           Integer cid){
        return new ResponseEntity<>(Result.success(workService.get80_90(wid,cid)), HttpStatus.OK);
    }

    @GetMapping("/seventies")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> get70_80(@RequestParam @Valid @NotNull(message = "作业号不能为空")
                                           Integer wid,
                                           @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                           Integer cid){
        return new ResponseEntity<>(Result.success(workService.get70_80(wid,cid)), HttpStatus.OK);
    }

    @GetMapping("/sixties")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> get60_70(@RequestParam @Valid @NotNull(message = "作业号不能为空")
                                           Integer wid,
                                           @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                           Integer cid){
        return new ResponseEntity<>(Result.success(workService.get60_70(wid,cid)), HttpStatus.OK);
    }

    @GetMapping("/failed")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> get_under_60(@RequestParam @Valid @NotNull(message = "作业号不能为空")
                                           Integer wid,
                                           @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                           Integer cid){
        return new ResponseEntity<>(Result.success(workService.get_under_60(wid,cid)), HttpStatus.OK);
    }

    @GetMapping("/homework/average")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> get_homework_avg(@RequestParam @Valid @NotNull(message = "课程号不能为空")
                                               Integer cid){
        return new ResponseEntity<>(Result.success(workService.get_homework_avg(cid)), HttpStatus.OK);
    }

    //所有没交的
    @GetMapping("/all/missed")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> get_missed_homework(@CurrentUser User user){
        return new ResponseEntity<>(Result.success(workService.get_missed_homework(user.getId())), HttpStatus.OK);
    }

    //本课程没交的
    @GetMapping("/course/missed")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> get_missed_homework_by_course(@CurrentUser User user,
                                                                @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                                Integer cid){
        return new ResponseEntity<>(Result.success(workService.get_missed_homework_by_course(user.getId(),cid)), HttpStatus.OK);
    }
}
