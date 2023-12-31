package com.example.evaluation.service;

import com.example.evaluation.controller.dto.HomeworkInfo;
import com.example.evaluation.controller.dto.NewHomeworkDto;
import com.example.evaluation.controller.dto.OpenPeerDto;
import com.example.evaluation.controller.dto.PeerDataDto;
import com.example.evaluation.entity.Homework;
import com.example.evaluation.entity.User;
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

    void createNewWork(NewHomeworkDto h, User user);

    void updateVisible(Integer wid, Integer cid, Integer status);

    void updateWorkInfo(Homework homework);

    void deleteHomework(Integer wid, Integer cid);

    List<HomeworkInfo> getAllWorkInfoByTid(Integer id, Integer cid);

    void updateOpenPeer(OpenPeerDto d);

    List<Map<String,Object>> getPeerDistribution(Integer cid);

    Object getOneWorkInfoBySid(Integer id, Integer wid, Integer cid);

    List<HomeworkInfo> getAllDraftWorkInfoByTid(Integer id, Integer cid);

    Integer getSubmitNumber(Integer wid, Integer cid);

    void updateSubmitNumber(Integer wid, Integer cid, Integer newNumber);

    String getDeadline(Integer wid, Integer cid);

    void addEvaNumber(Integer wid, Integer cid);

    void updateUrl(Integer wid, Integer cid, String url);

    Integer get90_100(Integer wid, Integer cid);

    Integer get80_90(Integer wid, Integer cid);

    Integer get70_80(Integer wid, Integer cid);

    Integer get60_70(Integer wid, Integer cid);

    Integer get_under_60(Integer wid, Integer cid);

    List<Map<String,String>> get_homework_avg(Integer cid);

    List<Map<String,String>> get_missed_homework(Integer sid);

    List<Map<String,String>> get_missed_homework_by_course(Integer sid, Integer cid);

    List<Map<String,Integer>> get_score_layers(Integer wid, Integer cid);

    List<List<Map<String,Integer>>> get_all_score_layers(Integer cid);
}
