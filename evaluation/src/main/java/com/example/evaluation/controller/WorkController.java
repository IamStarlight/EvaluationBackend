package com.example.evaluation.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.evaluation.controller.dto.SubmitDto;
import com.example.evaluation.controller.dto.TeacherEvaDto;
import com.example.evaluation.controller.dto.WorkDto;
import com.example.evaluation.domain.Homework;
import com.example.evaluation.domain.MyFile;
import com.example.evaluation.domain.Result;
import com.example.evaluation.service.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.evaluation.service.impl.SubmitServiceImpl;
import com.example.evaluation.service.impl.WorkServiceImpl;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/work")
public class WorkController {

    //文件磁盘路径
//    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    private WorkServiceImpl workService;

    @Autowired
    private SubmitServiceImpl submitService;

    //查询所有作业
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('1')")
    public ResponseEntity<Result> getAllWorkInfo(){
        return new ResponseEntity<>(Result.success(workService.list()), HttpStatus.OK);
    }

    //根据tid查询作业
//    @GetMapping("/all/teach")
//    @PreAuthorize("hasAnyAuthority('1')")
//    public ResponseEntity<Result> getAllWorkInfoByTid(@RequestParam @Valid String tid){
//        return new ResponseEntity<>(Result.success(workService.getAllWorkInfoByTid(tid)), HttpStatus.OK);
//    }

    //根据sid查询作业
    @GetMapping("/all/choose")
    @PreAuthorize("hasAnyAuthority('3')")
    public ResponseEntity<Result> getAllWorkInfoBySid(@RequestParam @Valid String sid){
        return new ResponseEntity<>(Result.success(workService.getAllWorkInfoBySid(sid)), HttpStatus.OK);
    }

    //根据cid查询作业
    @GetMapping("/all/course")
    @PreAuthorize("hasAnyAuthority('1','2','3')")
    public ResponseEntity<Result> getAllWorkInfoByCid(@RequestParam @Valid String cid){
        return new ResponseEntity<>(Result.success(workService.getAllWorkInfoByCid(cid)), HttpStatus.OK);
    }

    //根据wid查询作业 ok
    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('1','2','3')")
    public ResponseEntity<Result> getWorkInfoByWid(@RequestParam @Valid String wid){
        return new ResponseEntity<>(Result.success(workService.getWorkInfoByWid(wid)), HttpStatus.OK);
    }

    //管理员、教师更改编辑状态 ok
    @PostMapping("/edit/status")
    @PreAuthorize("hasAnyAuthority('1','2')")
    public ResponseEntity<Result> updateEditStatus(@RequestParam @Valid String wid, String status){
        return new ResponseEntity<>(Result.success(workService.updateEditStatus(wid,status)), HttpStatus.OK);
    }

    //管理员、教师更改互评状态 ok
    @PostMapping("/evaluate/status")
    @PreAuthorize("hasAnyAuthority('1','2')")
    public ResponseEntity<Result> updateEvaluateStatus(@RequestParam @Valid String wid,
                                                       @RequestParam @Valid String status){
        return new ResponseEntity<>(Result.success(workService.updateEvaluateStatus(wid,status)), HttpStatus.OK);
    }

    //编辑作业
    // TODO: 2023-11-01 500,400 why?
    @PostMapping("/edit")
    @PreAuthorize("hasAnyAuthority('1','2')")
    public ResponseEntity<Result> updateWorkInfo(@RequestParam @Valid String wid,
                                                 @RequestBody @Valid WorkDto wd){
        return new ResponseEntity<>(Result.success(workService.updateWorkInfo(wid,wd)), HttpStatus.OK);
    }

    //发布作业
    // TODO: 2023-11-02 400 why?
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('1','2')")
    public ResponseEntity<Result> setNewWork(@RequestBody @Valid WorkDto wd){
        return new ResponseEntity<>(Result.success(workService.setNewWork(wd)), HttpStatus.OK);
    }

    //交作业
    // TODO: 2023-11-01 500 saveorupdate
    @PostMapping("/submit")
    @PreAuthorize("hasAnyAuthority('3')")
    public ResponseEntity<Result> submitWork(@RequestBody @Valid SubmitDto submitDto){
        return new ResponseEntity<>(Result.success(submitService.submitWork(submitDto)), HttpStatus.OK);
    }

    //提交附件
    // TODO: 2023-10-31 uploadAttachments
//    @PostMapping("/upload")
//    @PreAuthorize("hasAnyAuthority('3')")
//    public ResponseEntity<Result> uploadAttachments(MultipartFile file){
//        return new ResponseEntity<>(Result.success(submitService.uploadAttachments(file)), HttpStatus.OK);
//    }

    /**
     * 由于我们上传上去的图片有重复的，所以我们需要去重，只让他们共享一个图像
     * 去重的思路为：将文件的二进制流转换为MD5编码，每当我们上传一个文件
     * 就将其二进制流MD5与数据库当中已保存的文件的二进制流MD5进行比较，
     * 相同就舍弃，不相同就将文件的信息保存至数据库，文件内容上传至文件夹。
     */
//    @PostMapping("/upload")
//    @PreAuthorize("hasAnyAuthority('1','2','3')")
//    public ResponseEntity<Result> upload(@RequestParam MultipartFile file){
//        return new ResponseEntity<>(Result.success(submitService.upload(file)), HttpStatus.OK);
//    }

    // TODO: 2023-10-31 downloadAttachments 

    //老师批改作业
    @PostMapping("/teacher/evaluation")
    @PreAuthorize("hasAnyAuthority('3')")
    public ResponseEntity<Result> teacherEvaluation(@RequestBody @Valid TeacherEvaDto td){
        return new ResponseEntity<>(Result.success(submitService.teacherEvaluation(td)), HttpStatus.OK);
    }

    // TODO: 2023-10-31 statistics 

    // TODO: 2023-11-03 作业状态更新通知

    // TODO: 2023-11-02 sendEmail 


}
