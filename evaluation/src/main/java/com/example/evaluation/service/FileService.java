package com.example.evaluation.service;

import com.example.evaluation.domain.MyFile;
import org.springframework.stereotype.Service;

@Service
public interface FileService {
    MyFile getFileByMd5(String md5);
}
