package com.example.evaluation.service;

import com.example.evaluation.controller.dto.AppealDto;
import com.example.evaluation.controller.dto.EvaDto;
import com.example.evaluation.entity.StuWork;
import com.github.jeffreyning.mybatisplus.service.IMppService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SubmitService extends IMppService<StuWork> {

    void generateSubmitList(Integer sid, Integer wid, Integer cid);

    void submitWork(StuWork stuWork);

    void teacherEvaluation(EvaDto td);

    List<Map<String,String>> getSubmitList(Integer wid, Integer cid);

    List<Map<String,String>> getMySubmit(Integer sid, Integer wid, Integer cid);

    List<Map<String,String>> getStuWorkTodoInfo(Integer id);

    void studentAppealing(AppealDto d);

    List<Map<String,String>> getHomeworkToRead(Integer tid, Integer cid);

    void updateSubmitWork(StuWork stuWork);

    void deleteOneSubmittedHomework(Integer id, Integer wid, Integer cid);
}
