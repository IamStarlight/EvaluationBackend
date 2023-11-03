package com.example.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.evaluation.entity.Homework;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WorkMapper extends BaseMapper<Homework> {
//    List<Map<String,String>> getAllWorkInfoByTid(@Param("tid") String tid);

    List<Map<String,String>> getAllWorkInfoBySid(@Param("sid") String sid);
}
