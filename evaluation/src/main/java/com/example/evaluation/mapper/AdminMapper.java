package com.example.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.evaluation.entity.Admin;
import com.example.evaluation.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Component
public interface AdminMapper extends BaseMapper<Admin> {
}
