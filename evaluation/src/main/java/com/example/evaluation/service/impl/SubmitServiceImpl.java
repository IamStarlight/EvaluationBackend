package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.controller.dto.SubmitDto;
import com.example.evaluation.domain.Submit;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.SubmitMapper;
import com.example.evaluation.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SubmitServiceImpl
        extends ServiceImpl<SubmitMapper, Submit>
        implements SubmitService {

    @Autowired
    private SubmitMapper submitMapper;

    @Autowired
    private WorkServiceImpl workService;

    @Override
    public boolean submitWork(SubmitDto sd) {
        boolean isOvertime = workService.checkOvertime(sd.getWid(), sd.getCid(), sd.getSubmitTime());

        LambdaUpdateWrapper<Submit> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Submit::getSid,sd.getSid())
                .eq(Submit::getWid,sd.getWid())
                .set(Submit::getURL,sd.getUrl())
                .set(Submit::getSubmitTime,sd.getSubmitTime())
                .set(Submit::getIsLate,isOvertime);

        boolean flag = saveOrUpdate(null,wrapper);
        if(flag) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }
}
