package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.domain.MyFile;
import com.example.evaluation.domain.User;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.FileMapper;
import com.example.evaluation.mapper.UserMapper;
import com.example.evaluation.service.FileService;
import com.example.evaluation.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, MyFile> implements FileService {

    @Override
    public MyFile getFileByMd5(String md5) {
        LambdaQueryWrapper<MyFile> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MyFile::getMd5, md5);
        List<MyFile> list = this.list(queryWrapper);
        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        return list.size() == 0 ? null : list.get(0);
    }
}
