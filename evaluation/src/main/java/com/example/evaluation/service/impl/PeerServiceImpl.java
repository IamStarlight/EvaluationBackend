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
}
