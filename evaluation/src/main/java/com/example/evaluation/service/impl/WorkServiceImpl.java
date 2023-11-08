package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.controller.dto.WorkDto;
import com.example.evaluation.entity.Homework;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.WorkMapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.evaluation.service.WorkService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WorkServiceImpl
        extends MppServiceImpl<WorkMapper, Homework>
        implements WorkService {

    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private  ScServiceImpl scService;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private EmailServiceImpl emailService;

//    @Override
//    public boolean updateEditStatus(String wid, String status) {
//        Homework one = getById(wid);
//        if(one == null) throw new ServiceException(HttpStatus.BAD_REQUEST.value(),"该作业不存在");
//
//        LambdaUpdateWrapper<Homework> wrapper = new LambdaUpdateWrapper<>();
//        wrapper.eq(Homework::getWid,wid)
//                .set(Homework::getStatus,status);
//
//        int flag = workMapper.update(null,wrapper);
//        if(flag >= 1) return true;
//        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "该作业不存在");
//    }

//    @Override
//    public boolean updateEvaluateStatus(String wid, String status) {
//        Homework one = getById(wid);
//        if(one == null) throw new ServiceException(HttpStatus.BAD_REQUEST.value(),"该作业不存在");
//
//        LambdaUpdateWrapper<Homework> wrapper = new LambdaUpdateWrapper<>();
//        wrapper.eq(Homework::getWid,wid)
//                .set(Homework::getStatus,status);
//
//        int flag = workMapper.update(null,wrapper);
//        if(flag >= 1) return true;
//        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "该作业不存在");
//    }

    @Override
    public boolean checkOvertime(String wid, String cid, Date submitTime) {
//        Homework one = getById(wid);
//        if(one == null)
//            throw new ServiceException(HttpStatus.BAD_REQUEST.value(),"该作业不存在");
//        return submitTime.compareTo(one.getEndTime()) > 0;
        return false;
    }

    @Override
    @Transactional
    public boolean updateWorkInfo(String wid, WorkDto wd) {
//        Homework one = getById(wid);
//        if(one == null) throw new ServiceException(HttpStatus.BAD_REQUEST.value(),"该作业不存在");
//
//        LambdaUpdateWrapper<Homework> wrapper = new LambdaUpdateWrapper<>();
//        wrapper.eq(Homework::getWid,wid)
//                .set(Homework::getTitle,wd.getTitle())
//                .set(Homework::getStartTime,wd.getStartTime())
//                .set(Homework::getEndTime,wd.getEndTime())
//                .set(Homework::getUrl,wd.getUrl());
//
//        int flag = workMapper.update(null,wrapper);
//        if(flag >= 1) return true;
//        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "该作业不存在");
        return false;
    }

    @Override
    public boolean setNewWork(WorkDto wd) {
        Homework one = new Homework();
        one.setCid(wd.getCid());
        one.setTitle(wd.getTitle());
        one.setDetails(wd.getDetails());
        one.setStartTime(wd.getStartTime());
        one.setEndTime(wd.getEndTime());
        one.setStatus(wd.getStatus());
        one.setUrl(wd.getUrl());

        if(save(one)){
            String cname = courseService.getById(wd.getCid()).getCname();
            List<Map<String,String>> list = scService.getAllSCList(wd.getCid());

            emailService.sendNewWorkNotice(cname,wd.getTitle(),list);
            return true;
        }return false;
    }

//    @Override
//    public List<Map<String, String>> getAllWorkInfoByTid(String tid) {
//        List<Map<String,String>> list = workMapper.getAllWorkInfoByTid(tid);
//        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
//        return list;
//    }

    @Override
    public List<Map<String, String>> getAllWorkInfoBySid(String sid) {
        List<Map<String,String>> list = workMapper.getAllWorkInfoBySid(sid);
        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        return list;
    }

    @Override
    public List<Homework> getAllWorkInfoByCid(String cid) {
        LambdaQueryWrapper<Homework> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Homework::getCid,cid);
        List<Homework> list = list(wrapper);
        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        return list;
    }


    @Override
    public List<Map<String, String>> getWorkInfoById(Integer wid, Integer cid) {
        List<Map<String,String>> list = workMapper.getWorkInfoById(wid,cid);
        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        return list;
    }
}
