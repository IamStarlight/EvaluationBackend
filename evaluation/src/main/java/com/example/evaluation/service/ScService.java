package com.example.evaluation.service;

import com.example.evaluation.entity.SC;
import com.github.jeffreyning.mybatisplus.service.IMppService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ScService extends IMppService<SC> {

    List<Integer> getAllSCListSid(Integer cid);

    List<Map<String,String>> getAllSCList(Integer cid);

    void deleteScStu(Integer sid, Integer cid);

    void addScStu(Integer sid, Integer cid);
}
