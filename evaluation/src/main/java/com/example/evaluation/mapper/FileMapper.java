package com.example.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.evaluation.domain.MyFile;
import com.example.evaluation.domain.StuWork;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Component
public interface FileMapper extends BaseMapper<MyFile> {
}
