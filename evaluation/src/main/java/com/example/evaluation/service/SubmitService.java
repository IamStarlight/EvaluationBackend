package com.example.evaluation.service;

import com.example.evaluation.controller.dto.SubmitDto;
import com.example.evaluation.controller.dto.TeacherEvaDto;
import com.example.evaluation.entity.Homework;
import com.example.evaluation.entity.StuWork;
import com.github.jeffreyning.mybatisplus.service.IMppService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface SubmitService extends IMppService<StuWork> {

    void submitWork(StuWork stuWork);

    void teacherEvaluation(TeacherEvaDto td);

    List<Map<String,String>> getSubmitList(Integer wid, Integer cid);
}
