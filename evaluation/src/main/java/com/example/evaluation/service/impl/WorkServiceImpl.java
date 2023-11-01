package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.controller.dto.WorkDto;
import com.example.evaluation.domain.Homework;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.WorkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.evaluation.service.WorkService;

import java.util.Date;

@Service
public class WorkServiceImpl
        extends ServiceImpl<WorkMapper, Homework>
        implements WorkService {

    @Autowired
    private WorkMapper workMapper;

    @Override
    public Homework getWorkInfoByWid(String wid) {
        LambdaUpdateWrapper<Homework> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Homework::getWid,wid);
        Homework one;
        try{
            one = getOne(wrapper);
        }catch (Exception e){
            log.error(e.toString());
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(),"系统错误");
        }return one;
    }

    // TODO: 2023-10-31 写法冗杂 
    @Override
    public boolean updateReleaseStatus(String wid) {
        Homework one = getWorkInfoByWid(wid);
        if(one == null)
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(),"该作业不存在");
        int status = one.getEditStatus();
        status = ~status;
        LambdaUpdateWrapper<Homework> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Homework::getWid,wid).set(Homework::getEditStatus,status);
        int flag = workMapper.update(null,wrapper);
        if(flag >= 1) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "该作业不存在");
    }

    @Override
    public boolean updateEvaluateStatus(String wid, String status) {
        LambdaUpdateWrapper<Homework> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Homework::getWid,wid).set(Homework::getEvaStatus,status);
        int flag = workMapper.update(null,wrapper);
        if(flag >= 1) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "该作业不存在");
    }

    @Override
    public boolean checkOvertime(String wid, String cid, Date submitTime) {
        Homework one = getWorkInfoByWid(wid);
        if(one == null)
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(),"该作业不存在");
        return submitTime.compareTo(one.getEndTime()) > 0;
    }

    @Override
    public boolean updateWorkInfo(WorkDto wd) {
        LambdaUpdateWrapper<Homework> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Homework::getWid,wd.getWid())
                .set(Homework::getStartTime,wd.getStartTime())
                .set(Homework::getEndTime,wd.getEndTime())
                .set(Homework::getEditStatus,wd.getEditStatus())
                .set(Homework::getEvaStatus,wd.getEvaluateStatus())
                .set(Homework::getURL,wd.getURL());
//        int flag = workMapper.update(null,wrapper);
        boolean flag = saveOrUpdate(null,wrapper);
        if(flag) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }
}
