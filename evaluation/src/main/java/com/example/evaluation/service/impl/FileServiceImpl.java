package com.example.evaluation.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.entity.MyFile;
import com.example.evaluation.mapper.FileMapper;
import com.example.evaluation.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl
        extends ServiceImpl<FileMapper, MyFile>
        implements FileService {

    //文件磁盘路径
    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Override
    public MyFile getFileByMd5(String md5) {
        LambdaQueryWrapper<MyFile> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MyFile::getMd5, md5);
        List<MyFile> list = this.list(queryWrapper);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public String upload(MultipartFile file) {
        //获取文件原始名称
        String originalFilename = file.getOriginalFilename();
        //获取文件的类型
        String type = FileUtil.extName(originalFilename);
//        log.info("文件类型是：" + type);
        //获取文件大小
        long size = file.getSize();
//        log.info("文件大小是：" + size);

        //文件存储的磁盘
        File uploadParentFile = new File(fileUploadPath);
        //判断文件目录是否存在
        if(!uploadParentFile.exists()) {
            //如果不存在就创建文件夹
            uploadParentFile.mkdirs();
        }

        //定义一个文件唯一标识码（UUID）
        String uuid = UUID.randomUUID().toString();
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);

        String url;
        String md5;

        try(InputStream inputStream = file.getInputStream()){
            // 获取文件的md5
            md5 = SecureUtil.md5(inputStream);
            // 从数据库查询是否存在相同的记录
            MyFile dbFiles = getFileByMd5(md5);
            if (dbFiles != null) { // 文件已存在
                url = dbFiles.getUrl();
            } else {
                // 上传文件到磁盘
                file.transferTo(uploadFile);
                // 数据库若不存在重复文件，则不删除刚才上传的文件
                url = "http://localhost:3309/file/download?url=" + fileUUID;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //存储至数据库
        MyFile saveFile = new MyFile();
        saveFile.setFname(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size/1024);//转成kb
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        save(saveFile);

        return url;
    }

    @Override
    public void download(String url, HttpServletResponse response) {
        MyFile f = getFileByUrl(url);
        //  新建文件流，从磁盘读取文件流
        try (FileInputStream fis = new FileInputStream(fileUploadPath+url);
             BufferedInputStream bis = new BufferedInputStream(fis);
             //  OutputStream 是文件写出流，讲文件下载到浏览器客户端
             OutputStream os = response.getOutputStream()) {
            // 新建字节数组，长度是文件的大小，比如文件 6kb, bis.available() = 1024 * 6
            byte[] bytes = new byte[bis.available()];
            // 从文件流读取字节到字节数组中
            bis.read(bytes);
            // 重置 response
            response.reset();
            /**
             * 如果是chrome浏览器，设置 response.setContentType("application/x-msdownload");
             * 或者 response.setContentType("application/octet-stream");
             * 或者 response.setHeader("Content-Disposition", "attachment");
             * 即可对服务器向客户端浏览器发送的文件进行下载
             */
            // 设置 response 的下载响应头
            response.setContentType("application/x-msdownload");
            // 注意，这里要设置文件名的编码，否则中文的文件名下载后不显示 attachment or inline
            response.setHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(url, "UTF-8"));
            // 写出字节数组到输出流
            os.write(bytes);
            // 刷新输出流
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public MyFile getFileByUrl(String url) {
        LambdaQueryWrapper<MyFile> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MyFile::getUrl, url);
        List<MyFile> list = this.list(queryWrapper);
        return list.isEmpty() ? null : list.get(0);
    }
}
