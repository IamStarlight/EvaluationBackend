package com.example.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.evaluation.entity.Homework;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WorkMapper extends MppBaseMapper<Homework> {
    List<Map<String,String>> getAllWorkInfoBySid(@Param("sid") String sid);

    List<Map<String, String>> getWorkInfoById(@Param("wid") Integer wid, @Param("cid") Integer cid);
}
