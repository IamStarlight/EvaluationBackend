package com.example.evaluation.service;

import com.example.evaluation.controller.dto.EvaDto;
import com.example.evaluation.entity.PeerEva;
import com.example.evaluation.entity.StuWork;
import com.github.jeffreyning.mybatisplus.service.IMppService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PeerService extends IMppService<PeerEva> {
    void peerEvaluation(Integer evaSid,EvaDto d);

    List<Map<String,String>> getEvaluatingStudentVision(Integer id);

    List<Map<String,String>> getBeEvaluatedStudentVision(Integer id);

    //    void peerEvaluation(Integer evaSid, EvaDto d);

    void addEvaluation(Integer evaSid, EvaDto d);

    void teaEvaluation(Integer tid, EvaDto d);

    List<StuWork> selectAllWork(Integer evaSid, Integer cid);

    List<StuWork> selectTeaAllWork(Integer tid, Integer cid);

    List<PeerEva> selectForTeacher(Integer evaSid, Integer wid);

    List<PeerEva> selectForStudent(Integer sid, Integer cid, Integer wid);

    StuWork selectOneWork(Integer sid, Integer cid, Integer wid);

    StuWork selectOneWorkForTea(Integer sid, Integer cid, Integer wid);
}
