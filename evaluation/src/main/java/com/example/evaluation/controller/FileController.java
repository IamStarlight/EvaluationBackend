package com.example.evaluation.controller;

import com.example.evaluation.entity.Result;
import com.example.evaluation.service.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Validated
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileServiceImpl fileService;

    //提交附件
    // TODO: 2023-10-31 uploadAttachments
    @PostMapping("/upload")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResponseEntity<Result> uploadAttachments(MultipartFile file){
        return new ResponseEntity<>(Result.success(fileService.uploadAttachments(file)), HttpStatus.OK);
    }

    // TODO: 2023-10-31 downloadAttachments
}
