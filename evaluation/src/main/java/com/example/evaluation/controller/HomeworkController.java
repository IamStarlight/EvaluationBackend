package com.example.evaluation.controller;

import com.example.evaluation.annotation.CurrentUser;
import com.example.evaluation.controller.dto.EvaDto;
import com.example.evaluation.controller.dto.NewHomeworkDto;
import com.example.evaluation.entity.Homework;
import com.example.evaluation.utils.Result;
import com.example.evaluation.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.evaluation.service.impl.SubmitServiceImpl;
import com.example.evaluation.service.impl.WorkServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Validated
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    private WorkServiceImpl workService;

    @Autowired
    private SubmitServiceImpl submitService;

//--------PostMapping------------------------------------

    //管理员、教师创建新作业 ok
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> createNewWork(@RequestBody @Valid NewHomeworkDto homework){
        workService.createNewWork(homework);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

//--------PutMapping------------------------------------

    // 管理员、教师：1存草稿 2发布 3截止 ok
    @PutMapping("/visible")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> updateVisible(@RequestParam @Valid @NotNull(message = "作业号不能为空")
                                                    Integer wid,
                                               @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                    Integer cid,
                                               @RequestParam @Valid @NotNull(message = "课程状态不能为空")
                                                    Integer status){
        workService.updateVisible(wid,cid,status);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    //管理员、教师编辑作业信息 ok
    @PutMapping("/edit")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> updateWorkInfo(@RequestBody @Valid Homework homework){
        workService.updateWorkInfo(homework);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    //老师批改作业 ok
    @PutMapping("/evaluate")
    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    public ResponseEntity<Result> teacherEvaluation(@RequestBody @Valid EvaDto d){
        submitService.teacherEvaluation(d);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

//--------GetMapping------------------------------------

    // 学生查询自己选的某节课的一个作业
    @GetMapping("/one")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> getOneWorkInfoBySid(@CurrentUser User user,
                                                      @RequestParam @Valid @NotNull(message = "作业号不能为空")
                                                            Integer wid,
                                                      @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                            Integer cid){
        return new ResponseEntity<>(Result.success(workService.getOneWorkInfoBySid(user.getId(),wid,cid)), HttpStatus.OK);
    }

    ///查询学生选的所有课程中所有未完成的作业 ok
    // TODO: 2023-11-22 修改完毕
    @GetMapping("/todo")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> getStuWorkTodoInfo(@CurrentUser User user){
        return new ResponseEntity<>(Result.success(submitService.getStuWorkTodoInfo(user.getId())), HttpStatus.OK);
    }

    //查询学生选的某课程布置的全部作业 ok
    // TODO: 2023-11-22 没懂这个接口,查出来的是已经写了的
    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> getStuWorkInfo(@CurrentUser User user,
                                                 @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                 Integer cid){
        return new ResponseEntity<>(Result.success(workService.getStuWorkInfo(user.getId(),cid)), HttpStatus.OK);
    }

    //根据课程号查询课程的全部作业 ok
    // TODO: 2023-11-21 /homework/bycourse
    @GetMapping("/course")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> getAllWorkInfoByCid(@RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                          Integer cid){
        return new ResponseEntity<>(Result.success(workService.getAllWorkInfoByCid(cid)), HttpStatus.OK);
    }

    //老师查询自己教的某课程的所有作业 ok
    @GetMapping("/teacher")
    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    public ResponseEntity<Result> getAllWorkInfoByTid(@CurrentUser User user,
                                                      @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                      Integer cid){
        return new ResponseEntity<>(Result.success(workService.getAllWorkInfoByTid(user.getId(),cid)), HttpStatus.OK);
    }

    //老师查询自己教的某课程的所有草稿箱里的作业 ok
    @GetMapping("/draft")
    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    public ResponseEntity<Result> getAllDraftWorkInfoByTid(@CurrentUser User user,
                                                      @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                      Integer cid){
        return new ResponseEntity<>(Result.success(workService.getAllDraftWorkInfoByTid(user.getId(),cid)), HttpStatus.OK);
    }

    // TODO: 2023-11-21 老师改别的成绩 ，重发互评？

    //老师根据作业号、课程号获得作业信息 ok （标题 截止日期 状态 提交人数 课堂人数）
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    public ResponseEntity<Result> getWorkInfoById(@RequestParam @Valid @NotNull(message = "作业号不能为空")
                                                        Integer wid,
                                                   @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                        Integer cid){
        return new ResponseEntity<>(Result.success(workService.getWorkInfoById(wid,cid)), HttpStatus.OK);
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

    // TODO: 2023-10-31 statistics 

    // TODO: 2023-11-03 作业状态更新通知

}
