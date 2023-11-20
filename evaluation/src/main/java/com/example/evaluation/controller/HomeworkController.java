package com.example.evaluation.controller;

import com.example.evaluation.annotation.CurrentUser;
import com.example.evaluation.controller.dto.AppealDto;
import com.example.evaluation.controller.dto.EvaDto;
import com.example.evaluation.controller.dto.OpenPeerDto;
import com.example.evaluation.entity.Homework;
import com.example.evaluation.utils.Result;
import com.example.evaluation.entity.StuWork;
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
import java.util.Date;

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
    public ResponseEntity<Result> createNewWork(@RequestBody @Valid Homework homework){
        workService.createNewWork(homework);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    //学生交作业 ok
    // TODO: 2023-11-20 currentsuser 
    @PostMapping("/submit")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> submitWork(@RequestBody @Valid StuWork stuWork){
        submitService.submitWork(stuWork);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

//--------PutMapping------------------------------------

    // TODO: 2023-11-20  // 管理员、教师1存草稿2发布3截止 ok
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

    // TODO: 2023-11-20  // 学生更新提交作业 ok
    @PostMapping("/submit/update")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> updateSubmitWork(@RequestBody @Valid StuWork stuWork){
        submitService.updateSubmitWork(stuWork);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    // TODO: 2023-11-20  //老师开启互评 ok
    @PutMapping("/open")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> updateOpenPeer(@RequestBody @Valid OpenPeerDto d){
        workService.updateOpenPeer(d);
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

    // TODO: 2023-11-20  //学生申诉 ok
    @PutMapping("/appeal")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> studentAppealing(@RequestBody @Valid AppealDto d){
        submitService.studentAppealing(d);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

//--------GetMapping------------------------------------

    // 学生查询自己选的所有课的所有作业 ?
//    @GetMapping("/student")
//    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
//    public ResponseEntity<Result> getAllWorkInfoBySid(@CurrentUser User user){
//        return new ResponseEntity<>(Result.success(workService.getAllWorkInfoBySid(user.getId())), HttpStatus.OK);
//    }

    // TODO: 2023-11-20  //查询学生选的所有课程中所有未完成的作业 ok
    @GetMapping("/todo")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> getStuWorkTodoInfo(@CurrentUser User user){
        return new ResponseEntity<>(Result.success(submitService.getStuWorkTodoInfo(user.getId())), HttpStatus.OK);
    }

    // TODO: 2023-11-20  //查询学生选的某课程布置的全部作业 ok
    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> getStuWorkInfo(@CurrentUser User user,
                                                 @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                 Integer cid){
        return new ResponseEntity<>(Result.success(workService.getStuWorkInfo(user.getId(),cid)), HttpStatus.OK);
    }

    //根据课程号查询课程的全部作业 ok
    @GetMapping("/bycourse")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> getAllWorkInfoByCid(@RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                          Integer cid){
        return new ResponseEntity<>(Result.success(workService.getAllWorkInfoByCid(cid)), HttpStatus.OK);
    }

    // TODO: 2023-11-20  //老师查询自己教的某课程的所有作业 ok
    @GetMapping("/teacher")
    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    public ResponseEntity<Result> getAllWorkInfoByTid(@CurrentUser User user,
                                                      @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                      Integer cid){
        return new ResponseEntity<>(Result.success(workService.getAllWorkInfoByTid(user.getId(),cid)), HttpStatus.OK);
    }

    //老师根据作业号、课程号获得作业信息 ok （标题 截止日期 状态 提交人数 课堂人数）
    @GetMapping("/list")
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

    // TODO: 2023-11-20  //一个学生一次作业的具体内容 ok
    @GetMapping("/detail")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> getMySubmit(@RequestParam @Valid @NotNull(message = "学号不能为空")
                                                    Integer sid,
                                              @RequestParam @Valid @NotNull(message = "作业号不能为空")
                                                    Integer wid,
                                              @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                    Integer cid){
        return new ResponseEntity<>(Result.success(submitService.getMySubmit(sid,wid,cid)), HttpStatus.OK);
    }

    // TODO: 2023-11-20  //老师查询自己某课程还没批改的作业 ok
    @GetMapping("/toread")
    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    public ResponseEntity<Result> getHomeworkToRead(@CurrentUser User user,
                                                      @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                                      Integer cid){
        return new ResponseEntity<>(Result.success(submitService.getHomeworkToRead(user.getId(),cid)), HttpStatus.OK);
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
