package com.example.evaluation.controller;

import com.example.evaluation.annotation.CurrentUser;
import com.example.evaluation.controller.dto.AppealDto;
import com.example.evaluation.controller.dto.EvaDto;
import com.example.evaluation.controller.dto.NewHomeworkDto;
import com.example.evaluation.controller.dto.OpenPeerDto;
import com.example.evaluation.entity.Homework;
import com.example.evaluation.entity.StuWork;
import com.example.evaluation.entity.User;
import com.example.evaluation.service.impl.SubmitServiceImpl;
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
@RequestMapping("/submit")
public class SubmitController {

    @Autowired
    private SubmitServiceImpl service;

    //--------PostMapping------------------------------------

    //学生交作业 ok
//    @PostMapping("/my")
//    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
//    public ResponseEntity<Result> submitWork(@CurrentUser User user,
//                                             @RequestBody @Valid StuWork stuWork){
//        stuWork.setSid(user.getId());
//        service.submitWork(stuWork);
//        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
//    }

//--------PutMapping------------------------------------

    // 学生更新提交作业 ok
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> updateSubmitWork(@CurrentUser User user,
                                                   @RequestBody @Valid StuWork stuWork){
        stuWork.setSid(user.getId());
        service.updateSubmitWork(stuWork);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

//--------GetMapping------------------------------------

    //管理员、教师查询作业提交名单 ok
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> getSubmitList(@RequestParam @Valid @NotNull(message = "作业号不能为空")
                                                Integer wid,
                                                @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                Integer cid){
        return new ResponseEntity<>(Result.success(service.getSubmitList(wid,cid)), HttpStatus.OK);
    }

    //管理员、教师查询未交作业名单 ok
    @GetMapping("/not")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> getNotSubmitList(@RequestParam @Valid @NotNull(message = "作业号不能为空")
                                                Integer wid,
                                                @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                Integer cid){
        return new ResponseEntity<>(Result.success(service.getNotSubmitList(wid,cid)), HttpStatus.OK);
    }

    //一个学生一次作业的具体内容 ok
    @GetMapping("/details")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> getMySubmit(@RequestParam @Valid @NotNull(message = "学号不能为空")
                                              Integer sid,
                                              @RequestParam @Valid @NotNull(message = "作业号不能为空")
                                              Integer wid,
                                              @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                              Integer cid){
        return new ResponseEntity<>(Result.success(service.getMySubmit(sid,wid,cid)), HttpStatus.OK);
    }

    //老师查询自己某课程还没批改的作业 ok
    @GetMapping("/read")
    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    public ResponseEntity<Result> getHomeworkToRead(@CurrentUser User user,
                                                    @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                    Integer cid){
        return new ResponseEntity<>(Result.success(service.getHomeworkToRead(user.getId(),cid)), HttpStatus.OK);
    }

    //--------DeleteMapping------------------------------------

    //删除提交的作业
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> deleteOneSubmitted(@CurrentUser User user,
                                                             @RequestParam @Valid @NotNull(message = "作业号不能为空")
                                                             Integer wid,
                                                             @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                             Integer cid){
        service.deleteOneSubmitted(user.getId(),wid,cid);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }
}
