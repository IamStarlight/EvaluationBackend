package com.example.evaluation.mapper;

import com.example.evaluation.entity.PeerEva;
import com.example.evaluation.entity.StuWork;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Component
public interface PeerMapper extends MppBaseMapper<PeerEva> {
    boolean peerEvaluation(@Param("evaSid") Integer evaSid, @Param("sid") Integer sid, @Param("wid") Integer wid, @Param("cid") Integer cid, @Param("grade") Integer grade, @Param("comments") String comments);
}
