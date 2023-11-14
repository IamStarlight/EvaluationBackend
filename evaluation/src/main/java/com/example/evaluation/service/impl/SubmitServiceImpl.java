package com.example.evaluation.service.impl;

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
    public void submitWork(StuWork w) {

        Date submitTime = new Date();
        w.setSubmitTime(submitTime);
        boolean isOvertime = workService.checkOvertime(w.getWid(), w.getCid(), w.getSubmitTime());
        // TODO: 2023-11-14 时间问题
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        System.out.println("!!!!!!!!!date="+submitTime+" format date="+formatter.format(submitTime));

        w.setLate(isOvertime);
        w.setSubmitTime(submitTime);

        if(!save(w)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
        }
    }

    @Override
    public void teacherEvaluation(EvaDto d) {
//        Integer tempGrade = (int) (d.getGrade() * 0.5);
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
}
