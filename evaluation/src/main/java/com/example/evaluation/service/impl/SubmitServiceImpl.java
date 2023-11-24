package com.example.evaluation.service.impl;

import com.example.evaluation.controller.dto.AppealDto;
import com.example.evaluation.controller.dto.EvaDto;
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

        if(!save(w)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
        }
    }

    @Override
    public void submitWork(StuWork w) {
        if(getMySubmit(w.getSid(),w.getWid(),w.getCid())!=null){
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "作业已提交");
        }
        Date submitTime = new Date();
        w.setSubmitTime(submitTime);
        boolean isOvertime = workService.checkOvertime(w.getWid(), w.getCid(), w.getSubmitTime());

        if(isOvertime){
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "作业已截止");
        }
        w.setSubmitTime(submitTime);

        if(!save(w)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
        }
        //提交作业之后，增加提交人数
        Integer newNumber = workService.getSubmitNumber(w.getWid(),w.getCid())+1;
        workService.updateSubmitNumber(w.getWid(),w.getCid(),newNumber);
        //更改提交状态
        mapper.setSubmitted(w.getSid(),w.getWid(),w.getCid());
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
    public List<Map<String,Object>> getSubmitListAll(Integer wid, Integer cid) {
        List<Map<String,Object>> list = mapper.getSubmitListAll(wid,cid);
//        if(list.isEmpty()) {
//            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
//        }
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
        Date appealTime = new Date();
        if(!mapper.studentAppealing(d.getSid(),d.getWid(),d.getCid(),d.getReason(),appealTime)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }
    }

    @Override
    public List<Map<String,String>> getHomeworkToRead(Integer tid, Integer cid) {
        List<Map<String,String>> list = mapper.getHomeworkToRead(tid,cid);
//        if(list.isEmpty()) {
//            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
//        }
        return list;
    }

    @Override
    public void updateSubmitWork(StuWork w) {
        Date submitTime = new Date();
        w.setSubmitTime(submitTime);

        boolean isOvertime = workService.checkOvertime(w.getWid(), w.getCid(), w.getSubmitTime());

        if(isOvertime){
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "作业已截止");
        }

        if(!updateByMultiId(w)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
        }

        if(mapper.getSubmit(w.getSid(),w.getWid(),w.getCid())==0){
            //提交作业之后，增加提交人数
            Integer newNumber = workService.getSubmitNumber(w.getWid(),w.getCid())+1;
            workService.updateSubmitNumber(w.getWid(),w.getCid(),newNumber);
            //更改提交状态
            mapper.setSubmitted(w.getSid(),w.getWid(),w.getCid());
        }
    }

    @Override
    public void deleteOneSubmitted(Integer id, Integer wid, Integer cid) {
//        StuWork idEntity = new StuWork();
//        idEntity.setSid(id);
//        idEntity.setWid(wid);
//        idEntity.setCid(cid);
//
//        if(!deleteByMultiId(idEntity)) {
//            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
//        }
        //删除提交之后，减少提交人数
        Integer newNumber = workService.getSubmitNumber(wid,cid)-1;
        workService.updateSubmitNumber(wid,cid,newNumber);
        //更改提交状态
        mapper.setNotSubmitted(id,wid,cid);
    }

    @Override
    public List<Map<String,String>> checkAppealing(Integer cid) {
        List<Map<String,String>> list = mapper.checkAppealing(cid);
//        if(list.isEmpty()) {
//            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
//        }
        return list;
    }

    @Override
    public List<Map<String,String>> checkOneAppealing(Integer sid, Integer wid, Integer cid) {
        List<Map<String,String>> list = mapper.checkOneAppealing(sid,wid,cid);
        if(list.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }
        return list;
    }

    @Override
    public Object getNotSubmitList(Integer wid, Integer cid) {
        List<Map<String,String>> list = mapper.getNotSubmitList(wid,cid);
        if(list.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
        return list;
    }

    @Override
    public void cancelAppealing(Integer sid, Integer wid, Integer cid) {
        if(!mapper.cancelAppealing(sid,wid,cid)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }
    }

    @Override
    public void teacherReply(AppealDto d) {
        if(!mapper.teacherReply(d.getSid(),d.getWid(), d.getCid(),d.getReply())) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }
    }

    @Override
    public List<StuWork> getAll() {
        return mapper.getAll();
    }

    @Override
    public void updateUrl(Integer sid,Integer wid, Integer cid, String url) {
        mapper.updateUrl(sid,wid,cid,url);
    }

    @Override
    public void updatePeerEvaluation(Integer sid, Integer wid, Integer cid) {
        mapper.updatePeerEvaluation(sid,wid,cid);
    }
}
