package com.example.evaluation.mapper;

import com.example.evaluation.entity.PeerEva;
import com.example.evaluation.entity.StuWork;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
@Component
public interface PeerMapper extends MppBaseMapper<PeerEva> {

    //增加一条评分记录
    boolean addEvaluation(@Param("evaSid") Integer evaSid, @Param("sid") Integer sid, @Param("wid") Integer wid, @Param("cid") Integer cid, @Param("grade") Integer grade, @Param("comments") String comments);

    //更新一条评分记录
    boolean peerEvaluation(@Param("evaSid") Integer evaSid, @Param("sid") Integer sid, @Param("wid") Integer wid, @Param("cid") Integer cid, @Param("grade") Integer grade, @Param("comments") String comments);

    //学生查看要评价的作业份数和作业内容
    List<StuWork> selectAllWork(@Param("sid") Integer sid, @Param("cid") Integer cid);

    //教师获取某位学生(互评学生)的评价名单、分数以及评论
    List<PeerEva> selectForTeacher(@Param("evaSid") Integer evaSid);

    //学生查看自己作业的互评列表（包括评分、评论)
    List<PeerEva> selectForStudent(@Param("sid") Integer sid, @Param("cid") Integer cid);
}
