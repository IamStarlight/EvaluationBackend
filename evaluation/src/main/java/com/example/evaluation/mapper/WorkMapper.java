package com.example.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.evaluation.controller.dto.HomeworkInfo;
import com.example.evaluation.entity.Homework;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface WorkMapper extends MppBaseMapper<Homework> {
    List<HomeworkInfo> getAllWorkInfoBySid(@Param("sid") Integer sid);

    List<HomeworkInfo> getAllWorkInfoByCid(@Param("cid") Integer cid);

    List<HomeworkInfo> getWorkInfoById(@Param("wid") Integer wid, @Param("cid") Integer cid);

    boolean createNewWork(@Param("wid") Integer wid,@Param("cid") Integer cid, @Param("title") String title, @Param("details") String details, @Param("url") String url, @Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("status") String status);

    boolean updateStatus(@Param("wid") Integer wid, @Param("cid") Integer cid, @Param("status") Integer status);
}
