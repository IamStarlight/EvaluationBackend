package com.example.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.evaluation.entity.StuWork;
import com.example.evaluation.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Component
public interface StudentMapper extends BaseMapper<Student> {
}
