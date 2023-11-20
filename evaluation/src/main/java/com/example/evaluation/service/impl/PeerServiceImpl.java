package com.example.evaluation.service.impl;

import com.example.evaluation.controller.dto.EvaDto;
import com.example.evaluation.entity.PeerEva;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.PeerMapper;
import com.example.evaluation.service.PeerService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PeerServiceImpl
        extends MppServiceImpl<PeerMapper, PeerEva>
        implements PeerService {

    @Autowired
    private PeerMapper mapper;

    @Override
    public void peerEvaluation(Integer evaSid,EvaDto d) {
        if(!mapper.peerEvaluation(evaSid,d.getSid(),d.getWid(),d.getCid(),d.getGrade(),d.getComments())) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }
    }

    @Override
    public List<Map<String,String>> getEvaluatingStudentVision(Integer id) {
        List<Map<String,String>> list = mapper.getEvaluatingStudentVision(id);
        if(list.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }return list;
    }

    @Override
    public List<Map<String,String>> getBeEvaluatedStudentVision(Integer id) {
        List<Map<String,String>> list = mapper.getBeEvaluatedStudentVision(id);
        if(list.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }return list;
    }
}
