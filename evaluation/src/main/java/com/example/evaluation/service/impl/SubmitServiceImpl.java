package com.example.evaluation.service.impl;

import com.example.evaluation.controller.dto.AppealDto;
import com.example.evaluation.controller.dto.EvaDto;
import com.example.evaluation.entity.Homework;
import com.example.evaluation.entity.StuWork;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.SubmitMapper;
import com.example.evaluation.service.SubmitService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SubmitServiceImpl
        extends MppServiceImpl<SubmitMapper, StuWork>
        implements SubmitService {

    @Autowired
    private SubmitMapper mapper;

    @Autowired
    private WorkServiceImpl workService;


    @Override
    public void generateSubmitList(Integer sid, Integer wid, Integer cid) {
        StuWork w = new StuWork();
        w.setSid(sid);
        w.setWid(wid);
        w.setCid(cid);
//        w.setSubmit(false);

        if(!save(w)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
        }
    }

    @Override
    public void submitWork(StuWork w) {

        Date submitTime = new Date();
        w.setSubmitTime(submitTime);
        boolean isOvertime = workService.checkOvertime(w.getWid(), w.getCid(), w.getSubmitTime());

        if(isOvertime){
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "作业已截止");
        }else{
            w.setSubmit(true);
        }
        w.setSubmitTime(submitTime);

        if(!save(w)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
        }
    }

    @Override
    public void teacherEvaluation(EvaDto d) {
        if(!mapper.teacherEvaluation(d.getSid(),d.getWid(),d.getCid(),d.getGrade(),d.getComments())) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }
    }

    @Override
    public List<Map<String,String>> getSubmitList(Integer wid, Integer cid) {
        List<Map<String,String>> list = mapper.getSubmitList(wid,cid);
        if(list.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
        return list;
    }

    @Override
    public List<Map<String,String>> getMySubmit(Integer sid, Integer wid, Integer cid) {
        // TODO: 2023-11-20 空的不显示 
        List<Map<String,String>> list = mapper.getMySubmit(sid,wid,cid);
        if(list.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
        return list;
    }

    @Override
    public List<Map<String,String>> getStuWorkTodoInfo(Integer id) {
        List<Map<String,String>> list = mapper.getStuWorkTodoInfo(id);
        if(list.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }
        return list;
    }

    @Override
    public void studentAppealing(AppealDto d) {
        if(!mapper.studentAppealing(d.getSid(),d.getWid(),d.getCid(),d.getReason())) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }
    }

    @Override
    public List<Map<String,String>> getHomeworkToRead(Integer tid, Integer cid) {
        List<Map<String,String>> list = mapper.getHomeworkToRead(tid,cid);
        if(list.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }
        return list;
    }

    @Override
    public void updateSubmitWork(StuWork w) {
        Date submitTime = new Date();
        w.setSubmitTime(submitTime);

        boolean isOvertime = workService.checkOvertime(w.getWid(), w.getCid(), w.getSubmitTime());

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        System.out.println("!!!!!!!!!date="+submitTime+" format date="+formatter.format(submitTime));

        if(isOvertime){
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "作业已截止");
        }else{
            w.setSubmit(true);
        }

        if(!updateByMultiId(w)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
        }
    }

    @Override
    public void deleteOneSubmittedHomework(Integer id, Integer wid, Integer cid) {
        StuWork idEntity = new StuWork();
        idEntity.setSid(id);
        idEntity.setWid(wid);
        idEntity.setCid(cid);
        // TODO: 2023-11-20 学生把提交的作业删了，issubmit也要改成0
        if(!deleteByMultiId(idEntity)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
    }
}
