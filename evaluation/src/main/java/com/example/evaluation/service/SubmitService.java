package com.example.evaluation.service;

import com.example.evaluation.controller.dto.EvaDto;
import com.example.evaluation.entity.StuWork;
import com.github.jeffreyning.mybatisplus.service.IMppService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SubmitService extends IMppService<StuWork> {

    void submitWork(StuWork stuWork);

    void teacherEvaluation(EvaDto td);

    List<Map<String,String>> getSubmitList(Integer wid, Integer cid);
}
