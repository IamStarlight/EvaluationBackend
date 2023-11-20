package com.example.evaluation.mapper;

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

    List<Map<String, String>> getMySubmit(@Param("sid") Integer sid, @Param("wid") Integer wid, @Param("cid") Integer cid);

    List<Map<String, String>> getStuWorkTodoInfo(@Param("id") Integer id);

    boolean studentAppealing(@Param("sid") Integer sid, @Param("wid") Integer wid, @Param("cid") Integer cid, @Param("reason") String reason);

    List<Map<String, String>> getHomeworkToRead(@Param("tid") Integer tid, @Param("cid") Integer cid);
}
