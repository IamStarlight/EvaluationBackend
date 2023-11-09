package com.example.evaluation.controller;

import com.example.evaluation.annotation.CurrentUser;
import com.example.evaluation.controller.dto.SubmitDto;
import com.example.evaluation.controller.dto.TeacherEvaDto;
import com.example.evaluation.controller.dto.WorkDto;
import com.example.evaluation.entity.Homework;
import com.example.evaluation.entity.Result;
import com.example.evaluation.entity.StuWork;
import com.example.evaluation.entity.User;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.evaluation.service.impl.SubmitServiceImpl;
import com.example.evaluation.service.impl.WorkServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@Validated
@RequestMapping("/homework")
public class WorkController {

    @Autowired
    private WorkServiceImpl workService;

    @Autowired
    private SubmitServiceImpl submitService;

//--------PostMapping------------------------------------

    //管理员、教师创建新作业 ok
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> createNewWork(@RequestBody @Valid Homework homework){
        workService.createNewWork(homework);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    //学生交作业 ok
    @PostMapping("/submit")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> submitWork(@RequestBody @Valid StuWork stuWork){
        submitService.submitWork(stuWork);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

//--------PutMapping------------------------------------

    //管理员、教师更改作业状态状态 ok
    @PutMapping("/edit/status")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> updateStatus(@RequestParam @Valid @NotNull(message = "作业号不能为空")
                                               Integer wid,
                                               @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                               Integer cid,
                                               @RequestParam @Valid @NotNull(message = "课程状态不能为空")
                                               Integer status){
        workService.updateStatus(wid,cid,status);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    //管理员、教师编辑作业 ok
    @PutMapping("/edit")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> updateWorkInfo(@RequestBody @Valid Homework homework){
        workService.updateWorkInfo(homework);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    //老师批改作业 ok
    @PutMapping("/evaluate")
    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    public ResponseEntity<Result> teacherEvaluation(@RequestBody @Valid TeacherEvaDto d){
        submitService.teacherEvaluation(d);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

//--------DeleteMapping------------------------------------
    //管理员、教师删除作业 ok
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> deleteHomework(@RequestParam @Valid @NotNull(message = "作业号不能为空")
                                                 Integer wid,
                                                 @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                 Integer cid){
        workService.deleteHomework(wid,cid);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

//--------GetMapping------------------------------------

    //学生查询自己的所有作业 ok
    // TODO: 2023-11-09 所有未完成的作业 
    @GetMapping("/student")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> getAllWorkInfoBySid(@CurrentUser User user){
        return new ResponseEntity<>(Result.success(workService.getAllWorkInfoBySid(user.getId())), HttpStatus.OK);
    }

    // TODO: 2023-11-08 学生查询自己某课程某作业提交的作业 

    //123根据课程号查询课程的全部作业 ok
    @GetMapping("/bycourse")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> getAllWorkInfoByCid(@RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                          Integer cid){
        return new ResponseEntity<>(Result.success(workService.getAllWorkInfoByCid(cid)), HttpStatus.OK);
    }

    //老师根据作业号、课程号获得作业信息 ok （标题 截止日期 状态 提交人数 课堂人数）
    @GetMapping("/teacher")
    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    public ResponseEntity<Result> getWorkInfoById(@RequestParam @Valid @NotNull(message = "作业号不能为空")
                                                        Integer wid,
                                                   @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                        Integer cid){
        return new ResponseEntity<>(Result.success(workService.getWorkInfoById(wid,cid)), HttpStatus.OK);
    }

    //管理员、教师查询作业提交名单 ok
    @GetMapping("/submitlist")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> getSubmitList(@RequestParam @Valid @NotNull(message = "作业号不能为空")
                                                Integer wid,
                                                @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                Integer cid){
        return new ResponseEntity<>(Result.success(submitService.getSubmitList(wid,cid)), HttpStatus.OK);
    }



    // TODO: 2023-11-09 更新提交作业 

    // TODO: 2023-10-31 statistics 

    // TODO: 2023-11-03 作业状态更新通知

    // TODO: 2023-11-02 sendEmail 

    

}
