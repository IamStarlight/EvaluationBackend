package com.example.evaluation.service.impl;

import com.example.evaluation.controller.dto.EvaDto;
import com.example.evaluation.entity.PeerEva;
import com.example.evaluation.entity.StuWork;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.PeerMapper;
import com.example.evaluation.service.PeerService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void addEvaluation(Integer evaSid, EvaDto d) {
        if(!mapper.addEvaluation(evaSid,d.getSid(),d.getWid(),d.getCid(),d.getGrade(),d.getComments())) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "添加失败");
        }
    }

    @Override
    public List<StuWork> selectAllWork(Integer evaSid, Integer cid) {
        //返回要评价的作业列表
        List<StuWork> list = mapper.selectAllWork(evaSid,cid);
        return list;
    }

    @Override
    public List<PeerEva> selectForTeacher(Integer evaSid) {
        //返回该学生互评过的作业
        List<PeerEva> list = mapper.selectForTeacher(evaSid);
        return list;
    }

    @Override
    public List<PeerEva> selectForStudent(Integer sid, Integer cid) {
        //返回该学生被互评作业得到的分数及评语
        List<PeerEva> list = mapper.selectForStudent(sid, cid);
        return list;
    }
}
