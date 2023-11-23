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

    List<Map<String,String>> getEvaluatingStudentVision(Integer evaSid, Integer wid, Integer cid);

    List<Map<String,String>> getBeEvaluatedStudentVision(Integer beEvaSid, Integer wid, Integer cid);

    List<Map<String,String>> getOneInfo(Integer evaSid, Integer beEvaSid, Integer wid, Integer cid);
}
