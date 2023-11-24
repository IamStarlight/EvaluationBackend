package com.example.evaluation.service;

import com.example.evaluation.entity.MyFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Service
public interface FileService {
    MyFile getFileByMd5(String md5);

    void upload(Integer sid,Integer wid, Integer cid, MultipartFile file);

    void upload(Integer wid, Integer cid, MultipartFile file);

    void download(String url, HttpServletResponse response);

    MyFile getFileByUrl(String url);
}
