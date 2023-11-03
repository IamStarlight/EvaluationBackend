package com.example.evaluation.service;

import com.example.evaluation.controller.dto.TeacherEvaDto;
import com.example.evaluation.controller.dto.WorkDto;
import com.example.evaluation.domain.Homework;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public interface WorkService {

    Homework getWorkInfoByWid(String wid);

    boolean updateEditStatus(String wid,String status);

    boolean updateEvaluateStatus(String wid, String status);

    boolean checkOvertime(String wid, String cid, Date submitTime);

    boolean updateWorkInfo(String wid, WorkDto wd);

    boolean setNewWork(WorkDto wd);

    List<Map<String,String>> getAllWorkInfoByTid(String tid);

    List<Map<String,String>> getAllWorkInfoBySid(String sid);

    List<Homework> getAllWorkInfoByCid(String cid);

    String upload(MultipartFile file);
}
