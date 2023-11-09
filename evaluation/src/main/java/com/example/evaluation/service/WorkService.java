package com.example.evaluation.service;

import com.example.evaluation.controller.dto.HomeworkInfo;
import com.example.evaluation.controller.dto.WorkDto;
import com.example.evaluation.entity.Homework;
import com.github.jeffreyning.mybatisplus.service.IMppService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public interface WorkService extends IMppService<Homework> {

    List<HomeworkInfo> getAllWorkInfoBySid(Integer sid);

    List<HomeworkInfo> getAllWorkInfoByCid(Integer cid);

    List<HomeworkInfo> getWorkInfoById(Integer wid, Integer cid);

    boolean checkOvertime(Integer wid, Integer cid, Date submitTime);

    void createNewWork(Homework homework);

    void updateStatus(Integer wid, Integer cid, Integer status);

    void updateWorkInfo(Homework homework);

    void deleteHomework(Integer wid, Integer cid);
}
