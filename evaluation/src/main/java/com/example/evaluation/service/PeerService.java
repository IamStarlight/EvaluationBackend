package com.example.evaluation.service;

import com.example.evaluation.controller.dto.EvaDto;
import com.example.evaluation.entity.PeerEva;
import com.example.evaluation.entity.StuWork;
import com.github.jeffreyning.mybatisplus.service.IMppService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PeerService extends IMppService<PeerEva> {
    void peerEvaluation(Integer evaSid, EvaDto d);

    void addEvaluation(Integer evaSid, EvaDto d);

    List<StuWork> selectAllWork(Integer evaSid, Integer cid);

    List<PeerEva> selectForTeacher(Integer evaSid);

    List<PeerEva> selectForStudent(Integer sid, Integer cid);
}


