package com.example.evaluation.mapper;

import com.example.evaluation.controller.dto.HomeworkInfo;
import com.example.evaluation.controller.dto.PeerDataDto;
import com.example.evaluation.entity.Homework;
import com.example.evaluation.enums.StatusEnum;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface WorkMapper extends MppBaseMapper<Homework> {
    List<HomeworkInfo> getAllWorkInfoBySid(@Param("sid") Integer sid);

    List<HomeworkInfo> getAllWorkInfoByCid(@Param("cid") Integer cid);

    List<HomeworkInfo> getWorkInfoById(@Param("wid") Integer wid, @Param("cid") Integer cid);

    boolean createNewWork(@Param("wid") Integer wid,@Param("cid") Integer cid, @Param("title") String title, @Param("details") String details, @Param("url") String url, @Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("status") String status);

    boolean updateVisible(@Param("wid") Integer wid, @Param("cid") Integer cid, @Param("status") StatusEnum status);

    List<HomeworkInfo> getAllWorkInfoByTid(@Param("id") Integer id, @Param("cid") Integer cid);

    List<HomeworkInfo> getStuWorkInfo(@Param("sid") Integer sid, @Param("cid") Integer cid);

    boolean updateOpenPeer(@Param("wid") Integer wid, @Param("cid") Integer cid, @Param("status") Integer status, @Param("ddl") Date ddl);

    List<HomeworkInfo> getOneWorkInfoBySid(@Param("id") Integer id, @Param("cid") Integer cid, @Param("wid") Integer wid);

    List<HomeworkInfo> getAllDraftWorkInfoByTid(@Param("id") Integer id, @Param("cid") Integer cid);

    Integer getSubmitNumber(@Param("wid") Integer wid, @Param("cid") Integer cid);

    void updateSubmitNumber(@Param("wid") Integer wid, @Param("cid") Integer cid, @Param("newNumber") Integer newNumber);

    void statusToRelease(@Param("wid") Integer wid, @Param("cid") Integer cid);

    void statusToEnd(@Param("wid") Integer wid, @Param("cid") Integer cid);

    String getDeadline(@Param("wid") Integer wid, @Param("cid") Integer cid);

    void evaStatusToEnd(@Param("wid") Integer wid, @Param("cid") Integer cid);

    void addEvaNumber(@Param("wid") Integer wid, @Param("cid") Integer cid);

    List<Homework> getHomeworkOpenEva();

    List<Homework> getHomeWorkUsingTimer();

    List<HomeworkInfo> getHomeworkHasReleased();

    List<Map<String,Object>> getPeerDistribution(@Param("cid") Integer cid);

    void updateUrl(@Param("wid") Integer wid, @Param("cid") Integer cid, @Param("url") String url);
}
