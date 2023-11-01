package com.example.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.evaluation.domain.Homework;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkMapper extends BaseMapper<Homework> {
}
