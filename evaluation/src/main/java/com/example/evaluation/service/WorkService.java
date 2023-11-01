package com.example.evaluation.service;

import com.example.evaluation.controller.dto.WorkDto;
import com.example.evaluation.domain.Homework;

import java.util.Date;

public interface WorkService {

    Homework getWorkInfoByWid(String wid);

    boolean updateReleaseStatus(String wid);

    boolean updateEvaluateStatus(String wid, String status);

    boolean checkOvertime(String wid, String cid, Date submitTime);

    boolean updateWorkInfo(WorkDto wd);
}
