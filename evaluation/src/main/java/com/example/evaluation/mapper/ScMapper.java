package com.example.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.evaluation.entity.Course;
import com.example.evaluation.entity.SC;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
@Component
public interface ScMapper extends MppBaseMapper<SC> {

    List<Map<String,String>> getAllSCList(@Param("cid") Integer cid);

    boolean deleteScStu(@Param("sid") Integer sid, @Param("cid") Integer cid);

    boolean addScStu(@Param("sid") Integer sid, @Param("cid") Integer cid);

    List<Map<String, String>> getOneSCStudent(@Param("sid") Integer sid, @Param("cid") Integer cid);
}
