package com.example.evaluation.service;

import com.example.evaluation.entity.SC;
import com.github.jeffreyning.mybatisplus.service.IMppService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ScService extends IMppService<SC> {

    List<Map<String,String>> getAllSCList(Integer cid);

    boolean deleteScStu(Integer sid, Integer cid);

    boolean addScStu(Integer sid, Integer cid);
}
