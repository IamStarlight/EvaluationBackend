package com.example.evaluation.service;

import com.example.evaluation.entity.MyFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
    MyFile getFileByMd5(String md5);

    String uploadAttachments(MultipartFile file);
}
