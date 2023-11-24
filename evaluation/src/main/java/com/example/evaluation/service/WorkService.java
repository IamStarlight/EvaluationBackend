package com.example.evaluation.service;

import com.example.evaluation.controller.dto.HomeworkInfo;
import com.example.evaluation.controller.dto.NewHomeworkDto;
import com.example.evaluation.controller.dto.OpenPeerDto;
import com.example.evaluation.controller.dto.PeerDataDto;
import com.example.evaluation.entity.Homework;
import com.github.jeffreyning.mybatisplus.service.IMppService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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

    void createNewWork(NewHomeworkDto homework);

    void updateVisible(Integer wid, Integer cid, Integer status);

    void updateWorkInfo(Homework homework);

    void deleteHomework(Integer wid, Integer cid);

    List<HomeworkInfo> getAllWorkInfoByTid(Integer id, Integer cid);

    void updateOpenPeer(OpenPeerDto d);

    void WriteIntoCsv(Integer cid);

    List<Map<String,Object>> getPeerDistribution(Integer cid);

    Object getOneWorkInfoBySid(Integer id, Integer wid, Integer cid);

    List<HomeworkInfo> getAllDraftWorkInfoByTid(Integer id, Integer cid);

    Integer getSubmitNumber(Integer wid, Integer cid);

    void updateSubmitNumber(Integer wid, Integer cid, Integer newNumber);

    String getDeadline(Integer wid, Integer cid);

    void addEvaNumber(Integer wid, Integer cid);

    void updateUrl(Integer wid, Integer cid, String url);
}
