package com.example.evaluation.mapper;

import com.example.evaluation.entity.PeerEva;
import com.example.evaluation.entity.StuWork;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface PeerMapper extends MppBaseMapper<PeerEva> {

    boolean peerEvaluation(@Param("evaSid") Integer evaSid, @Param("beEvaSid") Integer beEvaSid, @Param("wid") Integer wid, @Param("cid") Integer cid, @Param("grade") Integer grade, @Param("comments") String comments);

    List<Map<String, String>> getEvaluatingStudentVision(@Param("evaSid") Integer evaSid, @Param("wid") Integer wid, @Param("cid") Integer cid);

    List<Map<String, String>> getBeEvaluatedStudentVision(@Param("beEvaSid") Integer beEvaSid, @Param("wid") Integer wid, @Param("cid") Integer cid);

    List<Map<String, String>> getEvaluatingOne(@Param("evaSid") Integer evaSid, @Param("beEvaSid") Integer beEvaSid, @Param("wid") Integer wid, @Param("cid") Integer cid);

    List<Map<String, String>> getEvaluatedOne(@Param("evaSid") Integer evaSid, @Param("beEvaSid") Integer beEvaSid, @Param("wid") Integer wid, @Param("cid") Integer cid);
}
