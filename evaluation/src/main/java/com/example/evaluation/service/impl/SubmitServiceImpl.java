package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.controller.dto.SubmitDto;
import com.example.evaluation.controller.dto.TeacherEvaDto;
import com.example.evaluation.entity.StuWork;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.SubmitMapper;
import com.example.evaluation.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SubmitServiceImpl
        extends ServiceImpl<SubmitMapper, StuWork>
        implements SubmitService {

    @Autowired
    private SubmitMapper submitMapper;

    @Autowired
    private WorkServiceImpl workService;

    @Override
    public boolean submitWork(SubmitDto sd) {
        boolean isOvertime = workService.checkOvertime(sd.getWid(), sd.getCid(), sd.getSubmitTime());

        LambdaUpdateWrapper<StuWork> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(StuWork::getSid,sd.getSid())
                .eq(StuWork::getWid,sd.getWid())
                .set(StuWork::getURL,sd.getUrl())
                .set(StuWork::getSubmitTime,sd.getSubmitTime())
                .set(StuWork::getIsLate,isOvertime);

        boolean flag = saveOrUpdate(null,wrapper);
        if(flag) return true;
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

}
