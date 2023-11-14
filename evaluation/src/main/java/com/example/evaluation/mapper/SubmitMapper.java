package com.example.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.evaluation.entity.StuWork;
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
public interface SubmitMapper extends MppBaseMapper<StuWork> {
    List<Map<String, String>> getSubmitList(@Param("wid") Integer wid, @Param("cid") Integer cid);

    boolean teacherEvaluation(@Param("sid") Integer sid, @Param("wid") Integer wid, @Param("cid") Integer cid, @Param("teacherGrade") Integer teacherGrade, @Param("teacherComments") String teacherComments);

}
