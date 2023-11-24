package com.example.evaluation.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.evaluation.entity.MyFile;
import com.example.evaluation.mapper.FileMapper;
import com.example.evaluation.utils.Result;
import com.example.evaluation.service.impl.FileServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileServiceImpl fileService;

    @PostMapping("/upload/student")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> upload(@RequestParam @Valid @NotNull(message = "学号不能为空")
                                             Integer sid,
                                         @RequestParam @Valid @NotNull(message = "作业号不能为空")
                                             Integer wid,
                                         @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                             Integer cid,
                                         @RequestParam MultipartFile file) {
        fileService.upload(sid,wid,cid,file);
        return new ResponseEntity<>(Result.success(),HttpStatus.OK);
    }

    @PostMapping("/upload/teacher")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Result> upload(@RequestParam @Valid @NotNull(message = "作业号不能为空")
                                         Integer wid,
                                         @RequestParam @Valid @NotNull(message = "课程号不能为空")
                                         Integer cid,
                                         @RequestParam MultipartFile file) {
        fileService.upload(wid,cid,file);
        return new ResponseEntity<>(Result.success(),HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<Result> download(@RequestParam String url, HttpServletResponse response){
        fileService.download(url,response);
//        return new ResponseEntity<>(Result.success(),HttpStatus.OK);
        return null;
    }
}


