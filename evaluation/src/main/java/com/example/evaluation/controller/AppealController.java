package com.example.evaluation.controller;

import com.example.evaluation.controller.dto.AppealDto;
import com.example.evaluation.service.impl.SubmitServiceImpl;
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
@RequestMapping("/appeal")
public class AppealController {

    @Autowired
    private SubmitServiceImpl submitService;

    // TODO: 2023-11-21 重发互评

    //学生申诉 ok
    // TODO: 2023-11-21 /homework/appeal
    @PutMapping("/homework")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> studentAppealing(@RequestBody @Valid AppealDto d){
        submitService.studentAppealing(d);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    //老师查看申诉 ok
    // TODO: 2023-11-21 /homework/check
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> checkAppealing(@RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                 Integer cid){
        return new ResponseEntity<>(Result.success(submitService.checkAppealing(cid)), HttpStatus.OK);
    }

    //老师查看一个申诉 ok
    // TODO: 2023-11-21 /homework/checkone
    @GetMapping("/one")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> checkOneAppealing(@RequestParam @Valid @NotNull(message = "学号不能为空")
                                                    Integer sid,
                                                    @RequestParam @Valid @NotNull(message = "作业号不能为空")
                                                    Integer wid,
                                                    @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                    Integer cid){
        return new ResponseEntity<>(Result.success(submitService.checkOneAppealing(sid,wid,cid)), HttpStatus.OK);
    }
}
