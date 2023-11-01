package com.example.evaluation.service;

import com.example.evaluation.controller.dto.TeacherEvaDto;
import com.example.evaluation.controller.dto.WorkDto;
import com.example.evaluation.domain.Homework;

import java.util.Date;

public interface WorkService {

    Homework getWorkInfoByWid(String wid);

    boolean updateEditStatus(String wid,String status);

    boolean updateEvaluateStatus(String wid, String status);

    boolean checkOvertime(String wid, String cid, Date submitTime);

    boolean saveOrUpdateWorkInfo(WorkDto wd);
}
