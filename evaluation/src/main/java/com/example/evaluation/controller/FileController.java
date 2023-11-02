package com.example.evaluation.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.evaluation.domain.MyFile;
import com.example.evaluation.domain.Result;
import com.example.evaluation.service.impl.FileServiceImpl;
import io.lettuce.core.dynamic.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/file")
public class FileController {
    //文件磁盘路径
//    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Autowired
    private FileServiceImpl fileService;

    /**
     * 由于我们上传上去的图片有重复的，所以我们需要去重，只让他们共享一个图像
     * 去重的思路为：将文件的二进制流转换为MD5编码，每当我们上传一个文件
     * 就将其二进制流MD5与数据库当中已保存的文件的二进制流MD5进行比较，
     * 相同就舍弃，不相同就将文件的信息保存至数据库，文件内容上传至文件夹。
     */
    @PostMapping("/upload")
    public ResponseEntity<Result> upload(@RequestParam MultipartFile file) throws IOException {
        //获取文件原始名称
        String originalFilename = file.getOriginalFilename();
        //获取文件的类型
        String type = FileUtil.extName(originalFilename);
//        log.info("文件类型是：" + type);
        //获取文件大小
        long size = file.getSize();

        //文件存储的磁盘
        File uploadParentFile = new File(fileUploadPath);
        //判断文件目录是否存在
        if (!uploadParentFile.exists()) {
            //如果不存在就创建文件夹
            uploadParentFile.mkdirs();
        }

        //定义一个文件唯一标识码（UUID）
        String uuid = UUID.randomUUID().toString();
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);

        String url;
        // 获取文件的md5
        String md5 = SecureUtil.md5(file.getInputStream());
        // 从数据库查询是否存在相同的记录
        MyFile dbFiles = fileService.getFileByMd5(md5);
        if (dbFiles != null) { // 文件已存在
            url = dbFiles.getUrl();
        } else {
            // 上传文件到磁盘
            file.transferTo(uploadFile);
            // 数据库若不存在重复文件，则不删除刚才上传的文件
            url = "http://localhost:9090/file/" + fileUUID;
        }


        //存储至数据库
        MyFile saveFile = new MyFile();
        saveFile.setFname(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size / 1024);//转成kb
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        fileService.save(saveFile);

//        return url;
        return new ResponseEntity<>(Result.success(url), HttpStatus.OK);
    }
}
