package com.example.evaluation.service;

import com.example.evaluation.controller.dto.WorkDto;
import com.example.evaluation.domain.Homework;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface WorkService {

    Homework getWorkInfoByWid(String wid);

    boolean updateReleaseStatus(String wid);

    boolean updateEvaluateStatus(String wid, String status);

    boolean checkOvertime(String wid, String cid, Date submitTime);

    boolean updateWorkInfo(WorkDto wd);
}
