package com.example.evaluation.service;

import com.example.evaluation.controller.dto.HomeworkInfo;
import com.example.evaluation.controller.dto.OpenPeerDto;
import com.example.evaluation.controller.dto.WorkDto;
import com.example.evaluation.entity.Homework;
import com.github.jeffreyning.mybatisplus.service.IMppService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public interface WorkService extends IMppService<Homework> {

    List<HomeworkInfo> getAllWorkInfoBySid(Integer sid);

    List<HomeworkInfo> getAllWorkInfoByCid(Integer cid);

    List<HomeworkInfo> getWorkInfoById(Integer wid, Integer cid);

    List<HomeworkInfo> getStuWorkInfo(Integer sid, Integer cid);

    boolean checkOvertime(Integer wid, Integer cid, Date submitTime);

    void createNewWork(Homework h);

    void createNewWork(WorkDto homework);

    void updateVisible(Integer wid, Integer cid, Integer status);

    void updateWorkInfo(Homework homework);

    void deleteHomework(Integer wid, Integer cid);

    List<HomeworkInfo> getAllWorkInfoByTid(Integer id, Integer cid);

    void updateOpenPeer(OpenPeerDto d);
}
