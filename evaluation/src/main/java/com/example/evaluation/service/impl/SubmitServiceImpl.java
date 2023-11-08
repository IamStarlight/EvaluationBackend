package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.controller.dto.SubmitDto;
import com.example.evaluation.controller.dto.TeacherEvaDto;
import com.example.evaluation.entity.StuWork;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.SubmitMapper;
import com.example.evaluation.service.SubmitService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
    public boolean submitWork(SubmitDto d) {
//        boolean isOvertime = workService.checkOvertime(d.getWid(), d.getCid(), d.getSubmitTime());

        StuWork one = new StuWork();
        one.setSid(d.getSid());
        one.setWid(d.getWid());
        one.setCid(d.getCid());
        one.setDetails(d.getDetails());
        one.setUrl(d.getUrl());

        if(save(one)) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }

    @Override
    public boolean teacherEvaluation(TeacherEvaDto td) {
        LambdaUpdateWrapper<StuWork> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(StuWork::getWid,td.getWid())
                .set(StuWork::getTeacherGrade,td.getTeacherGrade())
                .set(StuWork::getTeacherComments,td.getTeacherComments());

        boolean flag = saveOrUpdate(null,wrapper);
        if(flag) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }

    @Override
    public List<Map<String,String>> getSubmitList(Integer wid, Integer cid) {
        List<Map<String,String>> list = mapper.getSubmitList(wid,cid);
        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        return list;
    }
}
