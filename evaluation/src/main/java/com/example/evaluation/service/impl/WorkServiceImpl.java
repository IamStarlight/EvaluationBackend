package com.example.evaluation.service.impl;

import com.example.evaluation.controller.dto.HomeworkInfo;
import com.example.evaluation.controller.dto.OpenPeerDto;
import com.example.evaluation.controller.dto.WorkDto;
import com.example.evaluation.entity.Homework;
import com.example.evaluation.enums.StatusEnum;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.WorkMapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.evaluation.service.WorkService;

import java.util.*;

@Service
public class WorkServiceImpl
        extends MppServiceImpl<WorkMapper, Homework>
        implements WorkService {

    @Autowired
    private WorkMapper mapper;

    @Autowired
    private ScServiceImpl scService;

    @Autowired
    private SubmitServiceImpl submitService;

    @Override
    public List<HomeworkInfo> getAllWorkInfoBySid(Integer sid) {
        List<HomeworkInfo> list = mapper.getAllWorkInfoBySid(sid);
        if(list.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
        return list;
    }

    @Override
    public List<HomeworkInfo> getAllWorkInfoByCid(Integer cid) {
        List<HomeworkInfo> list = mapper.getAllWorkInfoByCid(cid);
        if(list.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
        return list;
    }


    @Override
    public List<HomeworkInfo> getWorkInfoById(Integer wid, Integer cid) {
        List<HomeworkInfo> one = mapper.getWorkInfoById(wid,cid);
        if(one.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
        return one;
    }

    // TODO: 2023-11-10 批量导入选课列表
    @Override
    public List<HomeworkInfo> getStuWorkInfo(Integer sid, Integer cid) {
        List<HomeworkInfo> list = mapper.getStuWorkInfo(sid,cid);
        if(list.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
        return list;
    }

    @Override
    public boolean checkOvertime(Integer wid, Integer cid, Date submitTime) {
        Homework idEntity = new Homework();
        idEntity.setWid(wid);
        idEntity.setCid(cid);
        Homework one = selectByMultiId(idEntity);
        if(one == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"该作业不存在");
        }
        return submitTime.compareTo(one.getEndTime()) > 0;
    }

    @Override
    public void createNewWork(Homework h) {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date startTime = new Date();
        h.setStartTime(startTime);

//        String endTime = formatter.format(h.getEndTime());
//        h.setEndTime(endTime);

        if(!save(h)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "创建作业失败");
        }
        // is_submit: 创建一个作业后，stu_homework自动出现班级里所有人的记录，设置默认值为0
        List<Integer> list = scService.getAllSCListSid(h.getCid());
        for (Integer sid : list) {
            submitService.generateSubmitList(sid,h.getWid(),h.getCid());
        }
    }

    @Override
    public void createNewWork(WorkDto d) {
        Date startTime = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        System.out.println("!!!!!!!!!!!createNewWork date="+startTime+" "+"format="+formatter.format(startTime));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d.getEndTime());
        calendar.set(Calendar.HOUR,calendar.get(Calendar.HOUR)+8);
        Date time = calendar.getTime();

        Homework h = new Homework();
        h.setWid(d.getWid());
        h.setCid(d.getCid());
        h.setTitle(d.getTitle());
        h.setDetails(d.getDetails());
        h.setUrl(d.getUrl());
        h.setStartTime(startTime);
//        h.setEndTime(endTime);
        if(!save(h)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "创建作业失败");
        }
    }

    @Override
    public void updateVisible(Integer wid, Integer cid, Integer s) {
        StatusEnum status;
        switch (s){
            case 1:{
                status=StatusEnum.DRAFT;
                break;
            } case 2:{
                status=StatusEnum.RELEASED;
                break;
            } case 3:{
                status=StatusEnum.OVER;
                break;
            } default:{
                throw new ServiceException(HttpStatus.FORBIDDEN.value(),"错误的作业状态码");
            }
        }
        if(!mapper.updateVisible(wid,cid,status)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
    }

    @Override
    public void updateWorkInfo(Homework homework) {
        if(!updateByMultiId(homework)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
    }

    @Override
    public void deleteHomework(Integer wid, Integer cid) {
        Homework idEntity = new Homework();
        idEntity.setWid(wid);
        idEntity.setCid(cid);
        if(!deleteByMultiId(idEntity)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
    }

    @Override
    public List<HomeworkInfo> getAllWorkInfoByTid(Integer id, Integer cid) {
        List<HomeworkInfo> one = mapper.getAllWorkInfoByTid(id,cid);
        if(one.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }
        return one;
    }

    @Override
    public void updateOpenPeer(OpenPeerDto d) {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        System.out.println("!!!!!!!!!date="+submitTime+" format date="+formatter.format(submitTime));

//        String peer_ddl = formatter.format(ddl);
        if(!mapper.updateOpenPeer(d.getWid(),d.getCid(),d.getStatus(),d.getDdl())) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
    }


    @Override
    public Object getOneWorkInfoBySid(Integer id, Integer wid, Integer cid) {
        List<HomeworkInfo> one = mapper.getOneWorkInfoBySid(id,cid,wid);
        if(one.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }
        return one;
    }


}
