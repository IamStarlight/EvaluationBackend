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

    List<Map<String,Object>> getSubmitListAll(Integer wid, Integer cid);

    List<Map<String,String>> getMySubmit(Integer sid, Integer wid, Integer cid);

    List<Map<String,String>> getStuWorkTodoInfo(Integer id);

    void studentAppealing(AppealDto d);

    List<Map<String,String>> getHomeworkToRead(Integer tid, Integer cid);

    void updateSubmitWork(StuWork stuWork);

    void deleteOneSubmitted(Integer id, Integer wid, Integer cid);

    List<Map<String,String>> checkAppealing(Integer cid);

    List<Map<String,String>> checkOneAppealing(Integer sid, Integer wid, Integer cid);

    Object getNotSubmitList(Integer wid, Integer cid);

    void cancelAppealing(Integer sid, Integer wid, Integer cid);

    void teacherReply(AppealDto d);

    List<StuWork> getAll();

    void updateUrl(Integer sid,Integer wid, Integer cid, String url);

    void updatePeerEvaluation(Integer sid, Integer wid, Integer cid);

    List<Map<String, Object>> getSubmitList_for_statistics(Integer cid);
}
