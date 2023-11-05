package com.example.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.evaluation.entity.Student;
import com.example.evaluation.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Component
public interface TeacherMapper extends BaseMapper<Teacher> {
}
