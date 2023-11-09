package com.example.evaluation.service.impl;

import com.example.evaluation.controller.dto.HomeworkInfo;
import com.example.evaluation.entity.Homework;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.WorkMapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.evaluation.service.WorkService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WorkServiceImpl
        extends MppServiceImpl<WorkMapper, Homework>
        implements WorkService {

    @Autowired
    private WorkMapper mapper;

    @Autowired
    private  ScServiceImpl scService;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private EmailServiceImpl emailService;


    @Override
    public List<HomeworkInfo> getAllWorkInfoBySid(Integer sid) {
        List<HomeworkInfo> list = mapper.getAllWorkInfoBySid(sid);
        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        return list;
    }

    @Override
    public List<HomeworkInfo> getAllWorkInfoByCid(Integer cid) {
        List<HomeworkInfo> list = mapper.getAllWorkInfoByCid(cid);
        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        return list;
    }


    @Override
    public List<HomeworkInfo> getWorkInfoById(Integer wid, Integer cid) {
        List<HomeworkInfo> one = mapper.getWorkInfoById(wid,cid);
        if(one.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        return one;
    }

    @Override
    public boolean checkOvertime(Integer wid, Integer cid, Date submitTime) {
        Homework idEntity = new Homework();
        idEntity.setWid(wid);
        idEntity.setCid(cid);
        Homework one = selectByMultiId(idEntity);
        if(one == null)
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(),"该作业不存在");
        return submitTime.compareTo(one.getEndTime()) > 0;
    }

    @Override
    public void createNewWork(Homework h) {
        Date startTime = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println("!!!!!!!!!!!createNewWork date="+startTime+" "+"format="+formatter.format(startTime));

        h.setStartTime(startTime);

        if(!save(h))
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "创建作业失败");

//        if(!mapper.createNewWork(h.getWid(),h.getCid(),h.getTitle(),h.getDetails(),h.getUrl(),startTime,h.getEndTime(),h.getStatus()))
//            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "创建作业失败");
    }

    @Override
    public void updateStatus(Integer wid, Integer cid, Integer status) {
        if(!mapper.updateStatus(wid,cid,status))
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(),"作业状态更新失败");
    }

    @Override
    public void updateWorkInfo(Homework homework) {
        if(!updateByMultiId(homework))
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(),"作业信息更新失败");
    }

    @Override
    public void deleteHomework(Integer wid, Integer cid) {
        Homework idEntity = new Homework();
        idEntity.setWid(wid);
        idEntity.setCid(cid);
        if(!deleteByMultiId(idEntity))
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(),"作业删除失败");
    }
}
